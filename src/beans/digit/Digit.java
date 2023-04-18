/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans.digit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author duchu
 */
public class Digit extends JPanel{
    private static final int MIN = 0;
    private static final int MAX = 9;
    private static final Dimension PREFERRED_SIZE = new Dimension(30,64);
    private int index;
    private int value;
    private JLabel label;
    private CopyOnWriteArrayList<DigitListener> listenerList;
    
    public Digit(){
        super();
        this.value = 0;
        this.index = 0;
        this.listenerList = new CopyOnWriteArrayList<>();
        this.setLayout(new BorderLayout());
        this.label = new JLabel(String.valueOf(this.value));
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setVerticalTextPosition(SwingConstants.CENTER);
        this.label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.label.setFont(new Font("Arial", 0, 48));
        this.label.setText(String.valueOf(this.value));
        this.add(this.label,BorderLayout.CENTER);
        this.setPreferredSize(PREFERRED_SIZE);
        this.setMinimumSize(PREFERRED_SIZE);
        this.setSize(PREFERRED_SIZE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    public Digit(int value){
        super();
        this.value = value;
        this.index = 0;
        this.listenerList = new CopyOnWriteArrayList<>();
        this.setLayout(new BorderLayout());
        this.label = new JLabel(String.valueOf(this.value));
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setVerticalTextPosition(SwingConstants.CENTER);
        this.label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.label.setFont(new Font("Arial", 0, 48));
        this.label.setText(String.valueOf(this.value));
        this.add(this.label,BorderLayout.CENTER);
        this.setPreferredSize(PREFERRED_SIZE);
        this.setMinimumSize(PREFERRED_SIZE);
        this.setSize(PREFERRED_SIZE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    public Digit(int value, int index){
        super();
        this.value = value;
        this.index = index;
        this.listenerList = new CopyOnWriteArrayList<>();
        this.setLayout(new BorderLayout());
        this.label = new JLabel(String.valueOf(this.value));
        this.label.setVerticalAlignment(SwingConstants.CENTER);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setVerticalTextPosition(SwingConstants.CENTER);
        this.label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.label.setFont(new Font("Arial", 0, 48));
        this.label.setText(String.valueOf(this.value));
        this.add(this.label,BorderLayout.CENTER);
        this.setPreferredSize(PREFERRED_SIZE);
        this.setMinimumSize(PREFERRED_SIZE);
        this.setSize(PREFERRED_SIZE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    public void addDigitListener(DigitListener listener){
        if (!this.listenerList.contains(listener)){
            this.listenerList.add(listener);
        }
    }
    
    public void removeDigitListener(DigitListener listener){
        if (this.listenerList.contains(listener)){
            this.listenerList.remove(listener);
        }
    }
    
    public void fireDigitEvent(DigitEvent evt){
        this.listenerList.forEach(listener -> listener.valueChanged(evt));
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        if ((value >= Digit.MIN) && (value <= Digit.MAX)){
            int old = this.value;
            this.value = value;
            this.label.setText(String.valueOf(this.value));
            this.firePropertyChange("Value", old, value);
            this.fireDigitEvent(new DigitEvent(this));
            this.repaint();
        }
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public void setIndex(int index){
        if ((index >= 0) && (index != this.index)){
            int old = this.index;
            this.index = index;
            this.firePropertyChange("Index",old,index);
            this.fireDigitEvent(new DigitEvent(this));
            this.repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
}
