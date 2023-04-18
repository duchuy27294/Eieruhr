/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans.digitscontainer;

import beans.digit.Digit;
import beans.digit.DigitEvent;
import beans.digit.DigitListener;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import static java.lang.Math.pow;

/**
 *
 * @author duchu
 */
public class DigitsContainer extends JPanel implements DigitListener{
    private List<Digit> digits;
    private CopyOnWriteArrayList<DigitsContainerListener> listenerList;
    private int currentIndex;
    private int currentIndexValue;
    private int value;
    
    public DigitsContainer(){
        this.currentIndex = 0;
        this.digits = new ArrayList<>();
        Digit dg = new Digit(0,0);
        this.digits.add(dg);
        dg.addDigitListener(this);
        this.currentIndexValue = this.digits.get(this.currentIndex).getValue();
        this.value = this.currentIndexValue;
        this.listenerList = new CopyOnWriteArrayList<>();
        this.setLayout(new FlowLayout());
        this.digits.forEach(digit -> this.add(digit,0));
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    public DigitsContainer(int digitsNum){
        this.currentIndex = 0;
        this.digits = new ArrayList<>();
        for (int i = 0; i < digitsNum; i++){
            Digit dg = new Digit(0,0);
            this.digits.add(dg);
            dg.addDigitListener(this);
        }
        this.currentIndexValue = this.digits.get(this.currentIndex).getValue();
        this.value = this.currentIndexValue;
        this.listenerList = new CopyOnWriteArrayList<>();
        this.setLayout(new FlowLayout());
        this.digits.forEach(digit -> this.add(digit,0));
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    public Digit getDigit(int index){
        try{
            return this.digits.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    
    public int getDigitValue(int index){
        try{
            return this.digits.get(index).getValue();
        }
        catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
    
    public void setDigitValue(int index,int value){
        try{
            int old = this.value;
            this.digits.get(index).setValue(value);
            this.updateValue();
            this.firePropertyChange("Value",old,this.value);
            this.repaint();
        }
        catch (ArrayIndexOutOfBoundsException e){

        }
    }
    
    public int getCurrentIndex(){
        return this.currentIndex;
    }
    
    public void setCurrentIndex(int index){
        if ((index >= 0) && (index <= this.digits.size()-1) && (this.currentIndex != index)){
            int old = this.currentIndex;
            this.currentIndex = index;
            this.firePropertyChange("Current index",old,index);
            this.repaint();
        }
    }
    
    public int getCurrentIndexValue(){
        return this.digits.get(this.currentIndex).getValue();
    }
    
    public void setCurrentIndexValue(int value){
        int old = this.currentIndexValue;
        int oldValue = this.value;
        this.digits.get(this.currentIndex).setValue(value);
        this.currentIndexValue = this.digits.get(this.currentIndex).getValue();
        //this.updateValue();
        this.firePropertyChange("Current index value",old,this.currentIndexValue);  
        this.firePropertyChange("Value",oldValue,this.value);
        this.repaint();
    }
    
    public int count(){
        return this.digits.size();
    }
    
    public void setCount(int count){
        if ((count > 0) && (count != this.digits.size())){
            int old = this.digits.size();
            int oldVal = this.value;
            if (count < this.digits.size()){
                //this.digits = this.digits.subList(0,count-1);
                for (int i = old - 1; i >= count; i--){
                    this.remove(0);
                }
                this.digits = this.digits.subList(0,count);
                this.updateValue();
                if (this.currentIndex > this.digits.size() - 1){
                    int oldIdx = this.currentIndex;
                    int oldIdxVal = this.currentIndexValue;
                    this.currentIndex = this.digits.size() - 1;
                    this.currentIndexValue = this.digits.get(this.currentIndex).getValue();
                    this.firePropertyChange("Current index",oldIdx,this.currentIndex);
                    this.firePropertyChange("Current index Value",oldIdxVal,this.currentIndexValue);
                }
            }
            else{
                for (int i = this.digits.size(); i < count; i++){
                    Digit dg = new Digit(0,i);
                    this.digits.add(dg);
                    this.add(dg,0);
                    dg.addDigitListener(this);
                }
                this.updateValue();
            }
            this.firePropertyChange("Count",old,this.digits.size());
            this.firePropertyChange("Value",oldVal,this.value);
            this.fireDigitsContainerEvent(new DigitsContainerEvent(this));
            this.repaint();
        }
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        if ((value >= 0) && (value != this.value)){
            int old = this.value;
            int oldCount = this.count();
            String valStr = String.valueOf(value);
            int digitsNum = valStr.length();

            if (digitsNum >= oldCount){
                if (digitsNum > oldCount){
                    for (int i = 0; i < digitsNum - oldCount; i++){
                        Digit dg = new Digit(0,oldCount+i);
                        this.digits.add(dg);
                        this.add(dg,0);
                    }
                    this.firePropertyChange("Count",oldCount,digitsNum);
                }
            }
            else{
                for (int i = oldCount-1; i >= digitsNum; i--){
                    this.setDigitValue(i,0);
                }
            }
            for (int i = 0; i < digitsNum; i++){
                int val = Character.getNumericValue(valStr.charAt(digitsNum-1-i));
                this.setDigitValue(i,val);
            }
            this.value = value;
            this.firePropertyChange("Value",old,value);
        }
    }
    

    @Override
    public void valueChanged(DigitEvent evt) {
        this.updateValue();
        this.fireDigitsContainerEvent(new DigitsContainerEvent(this));
    }
    
    private void updateValue(){
        int val = 0;
        for (Digit digit:this.digits){
            val += (digit.getValue() * pow(10,digit.getIndex()));
        }
        this.value = val;
    }
    
    public void addDigitsContainerListener(DigitsContainerListener listener){
        if (!this.listenerList.contains(listener)){
            this.listenerList.add(listener);
        }
    }
    
    public void removeDigitsContainerListener(DigitsContainerListener listener){
        if (this.listenerList.contains(listener)){
            this.listenerList.remove(listener);
        }
    }
    
    public void fireDigitsContainerEvent(DigitsContainerEvent evt){
        this.listenerList.forEach(listener -> listener.valueChanged(evt));
    }
    
}
