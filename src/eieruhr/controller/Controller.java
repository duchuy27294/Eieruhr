/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eieruhr.controller;

import eieruhr.model.EieruhrModel;
import eieruhr.model.TimerAbgelaufenEvent;
import eieruhr.view.View;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author duchu
 */
public class Controller implements ChangeListener{
    private EieruhrModel model;
    private View view;
  
    public Controller(EieruhrModel model, View view){
        this.model = model;
        this.view = view;
        this.view.getBtnPause().setEnabled(false);
        this.view.getBtnStop().setEnabled(false);
        this.view.getBtnUnpause().setEnabled(false);   
    }
    
    public void registerEvents(){
        this.view.getBtnStart().addActionListener(this::start);
        this.view.getBtnPause().addActionListener(this::pause);
        this.view.getBtnStop().addActionListener(this::stop);
        this.view.getBtnUnpause().addActionListener(this::unpause);
        this.view.getBtnSet().addActionListener(this::setTime);
        this.view.getSpinnerHour().addChangeListener(this);
        this.view.getSpinnerMinute().addChangeListener(this);
        this.view.getSpinnerSecond().addChangeListener(this);
    }
    
    private void start(ActionEvent evt){
        this.view.getLed().setEingeschaltet(false);
        this.model.start();
        this.view.getBtnStart().setEnabled(false);
        this.view.getBtnPause().setEnabled(true);
        this.view.getBtnUnpause().setEnabled(true);
        this.view.getBtnStop().setEnabled(true);
        this.view.getSpinnerHour().setEnabled(false);
        this.view.getSpinnerMinute().setEnabled(false);
        this.view.getSpinnerSecond().setEnabled(false);
    }
    
    private void pause(ActionEvent evt){
        this.model.pause();
        this.view.getBtnStart().setEnabled(false);
        this.view.getBtnPause().setEnabled(false);
        this.view.getBtnUnpause().setEnabled(true);
        this.view.getBtnStop().setEnabled(true);
    }
    
    private void unpause(ActionEvent evt){
        this.model.unpause();
        this.view.getBtnStart().setEnabled(false);
        this.view.getBtnPause().setEnabled(true);
        this.view.getBtnUnpause().setEnabled(false);
        this.view.getBtnStop().setEnabled(true);
    }
    
    private void stop(ActionEvent evt){
        this.model.stop();
        this.view.getBtnStart().setEnabled(true);
        this.view.getBtnPause().setEnabled(false);
        this.view.getBtnUnpause().setEnabled(false);
        this.view.getBtnStop().setEnabled(false);
        this.view.getSpinnerHour().setEnabled(true);
        this.view.getSpinnerMinute().setEnabled(true);
        this.view.getSpinnerSecond().setEnabled(true);
    }
    
    private void setTime(ActionEvent evt){
        this.model.setHour((int)this.view.getSpinnerHour().getValue());
        this.model.setMinute((int)this.view.getSpinnerMinute().getValue());
        this.model.setSecond((int)this.view.getSpinnerSecond().getValue());
        this.view.getLed().setEingeschaltet(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner js = (JSpinner)e.getSource();
        js.repaint();
        this.view.pack();
    }

}
