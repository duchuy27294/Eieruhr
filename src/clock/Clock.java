/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author duchu
 */
public class Clock {
    private static final int MAX_HOUR = 99;
    private int hour;
    private int minute;
    private int second;
    private CopyOnWriteArrayList<ClockListener> listenerList;
    
    public Clock(){
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
        this.listenerList = new CopyOnWriteArrayList<>();
    }
    
    public void addClockListener(ClockListener listener){
        if (!listenerList.contains(listener)){
            this.listenerList.add(listener);
        }
    }
    
    public void removeClockListener(ClockListener listener){
        if (this.listenerList.contains(listener)){
            this.listenerList.remove(listener);
        }
    }
    
    public void fireClockEvent(ClockEvent evt){
        this.listenerList.forEach(listener -> listener.timeChanged(evt));
    }
    
    public Clock(int h, int m, int s){
        this.hour = h;
        this.minute = m;
        this.second = s;
    }
    
    public int getHour(){
        return this.hour;
    }
    
    public void setHour(int h){
        this.hour = h;
        this.fireClockEvent(new ClockEvent(this));
    }
    
    public int getMinute(){
        return this.minute;
    }
    
    public void setMinute(int m){
        this.minute = m;
        this.fireClockEvent(new ClockEvent(this));
    }
    
    public int getSecond(){
        return this.second;
    }
    
    public void setSecond(int s){
        this.second = s;
        this.fireClockEvent(new ClockEvent(this));
    }
    
    public void setTime(int h,int m,int s){
        this.hour = h;
        this.minute = m;
        this.second = s;
    }
}
