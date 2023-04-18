/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans.digitalclock;

import beans.digitscontainer.DigitsContainer;
import clock.Clock;
import clock.ClockEvent;
import clock.ClockListener;
import java.awt.FlowLayout;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author duchu
 */
public class DigitalClock extends JPanel implements ClockListener {
    private Clock model;
    private final DigitsContainer secondDigits;
    private final DigitsContainer minuteDigits;
    private final DigitsContainer hourDigits;
    private CopyOnWriteArrayList<DigitalClockListener> listenerList;
    
    public DigitalClock(){
        this.model = null;
        this.secondDigits = new DigitsContainer(2);
        this.minuteDigits = new DigitsContainer(2);
        this.hourDigits = new DigitsContainer(2);
        this.setLayout(new FlowLayout());
        this.add(this.hourDigits);
        this.add(new JLabel(":"));
        this.add(this.minuteDigits);
        this.add(new JLabel(":"));
        this.add(this.secondDigits);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.listenerList = new CopyOnWriteArrayList<>();
    }
    
    public void setModel(Clock model){
        this.model = model;
        this.model.addClockListener(this);
        this.updateTime();
    }
    
    public int getHour(){
        if(this.model != null){
            return this.model.getHour();
        }
        else{
            return 0;
        }
    }
    
    public void setHour(int value){
        if ((value >= 0) && (value <= 99)){
            if (this.model != null){
                this.model.setHour(value);
            }
            else{
                if (value <= 9){
                    this.hourDigits.setDigitValue(0,value);
                    this.hourDigits.setDigitValue(1,0);
                }
                else{
                    this.hourDigits.setValue(value);
                }
            }
        }
    }
    
    public int getMinute(){
        if(this.model != null){
            return this.model.getMinute();
        }
        else{
            return 0;
        }
    }
    
    public void setMinute(int value){
        if ((value >= 0) && (value <= 59)){
            if (this.model != null){
                this.model.setMinute(value);
            }
            else{
                if (value <= 9){
                    this.minuteDigits.setDigitValue(0,value);
                    this.minuteDigits.setDigitValue(1,0);
                }
                else{
                    this.minuteDigits.setValue(value);
                }
            }
        }
    }
    
    
    public int getSecond(){
        if(this.model != null){
            return this.model.getSecond();
        }
        else{
            return 0;
        }
    }
    
    public void setSecond(int value){
        if ((value >= 0) && (value <= 59)){
            if (this.model != null){
                this.model.setSecond(value);
            }
            else{
                if (value <= 9){
                    this.secondDigits.setDigitValue(0,value);
                    this.secondDigits.setDigitValue(1,0);
                }
                else{
                    this.secondDigits.setValue(value);
                }
            }
        }
    }
    
    private void updateTime(){
        if (model != null){
            if (this.model.getSecond() <= 9){
                this.secondDigits.setDigitValue(0,this.model.getSecond());
                this.secondDigits.setDigitValue(1,0);
            }
            else{
                this.secondDigits.setValue(this.model.getSecond());
            }
            if (this.model.getMinute() <= 9){
                this.minuteDigits.setDigitValue(0,this.model.getMinute());
                this.minuteDigits.setDigitValue(1,0);
            }
            else{
                this.secondDigits.setValue(this.model.getMinute());
            }
            if (this.model.getHour() <= 9){
                this.hourDigits.setDigitValue(0,this.model.getHour());
                this.hourDigits.setDigitValue(1,0);
            }
            else{
                this.hourDigits.setValue(this.model.getHour());
            }
        }
    }
    
    public void addDigitalClockListener(DigitalClockListener listener){
        if (!listenerList.contains(listener)){
            this.listenerList.add(listener);
        }
    }
    
    public void removeDigitalClockListener(DigitalClockListener listener){
        if (this.listenerList.contains(listener)){
            this.listenerList.remove(listener);
        }
    }
    
    public void fireDigitalClockEvent(DigitalClockEvent evt){
        this.listenerList.forEach(listener -> listener.timeChanged(evt));
    }

    @Override
    public void timeChanged(ClockEvent evt) {
        this.updateTime();
    }
}
