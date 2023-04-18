/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eieruhr.model;

import clock.Clock;
import clock.ClockEvent;
import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duchu
 */
public class EieruhrModel extends Clock implements Serializable, Runnable{
    private static final int MAX_HOUR = 99;
    private Future task;
    private ExecutorService eService;
    private AtomicBoolean running;
    private AtomicBoolean end;
    private final Object LOCK = new Object();
    private CopyOnWriteArrayList<EieruhrListener> listenerList;
    private SubmissionPublisher<Integer> publisher;
    private int totalTime;
    private int timeLeft;
    
    public EieruhrModel(){
        super();
        this.task = null;
        this.eService = Executors.newSingleThreadExecutor();
        this.running = new AtomicBoolean(false);
        this.end = new AtomicBoolean(true);
        this.listenerList = new CopyOnWriteArrayList<>();
        this.publisher = new SubmissionPublisher<>();
        this.totalTime = 0;
        this.timeLeft = 0;
    }
    
    public void addSubscription(Flow.Subscriber<Integer> subscriber){
        publisher.subscribe(subscriber);
    }
    
    public int getTimeLeft(){
        return this.timeLeft;
    }
    
    public int getTotalTime(){
        return this.totalTime;
    }
    
    public void start(){
        this.totalTime = this.getHour()*3600 + this.getMinute()*60 + this.getSecond();
        this.timeLeft = this.totalTime;
        this.running.set(true);
        if (this.task == null) {
            this.task = eService.submit(this);
        }
        else{
            synchronized(this.LOCK){
                this.LOCK.notifyAll();
            }
        }
    }
    
    public void unpause(){
        this.running.set(true);
        synchronized(this.LOCK){
            this.LOCK.notifyAll();
        }
    }
    
    public void pause(){
        this.running.set(false);
        this.end.set(false);
    }
    
    public void stop(){
        this.running.set(false);
        this.end.set(true);
        this.setHour(0);
        this.setMinute(0);
        this.setSecond(0);
        this.totalTime = 0;
        this.timeLeft = 0;
    }

    @Override
    public void run() {
        while(true){
            while(!this.running.get()){
                try{
                    synchronized(this.LOCK){
                        this.LOCK.wait();
                    }
                }  
                catch (InterruptedException e){

                }
            }
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException ex){
                Logger.getLogger(EieruhrModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.isEnd()){
                this.stop();
                this.fireTimerAbgelaufenEvent(new TimerAbgelaufenEvent(this));
            }
            else{
                if (this.getSecond() == 0){
                    this.setSecond(59);
                    if (this.getMinute() == 0){
                        if (this.getHour() != 0){
                            this.setMinute(59);
                            this.setHour(this.getHour()-1);
                        }
                    }
                    else{
                        this.setMinute(this.getMinute()-1);
                    }
                }
                else{
                    this.setSecond(this.getSecond()-1);
                }
                this.timeLeft -= 1;
                this.publisher.submit(this.timeLeft);
                this.fireClockEvent(new ClockEvent(this));
            }       
        }
    }
    
    public boolean isEnd(){
        return (this.getHour() == 0) && (this.getMinute() == 0) && (this.getSecond() == 0);
    }
    
    public void addEieruhrListener(EieruhrListener listener){
        if (!this.listenerList.contains(listener)){
            this.listenerList.add(listener);
        }
    }
    
    public void removeEieruhrListener(EieruhrListener listener){
        if(this.listenerList.contains(listener)){
            this.listenerList.remove(listener);
        }
    }
    
    public void fireTimerAbgelaufenEvent(TimerAbgelaufenEvent evt){
        this.listenerList.forEach(listener -> listener.timerAbgelaufen(evt));
    }
}
