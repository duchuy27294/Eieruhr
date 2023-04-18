/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eieruhr.controller;

import eieruhr.model.EieruhrListener;
import eieruhr.model.EieruhrModel;
import eieruhr.model.TimerAbgelaufenEvent;
import eieruhr.view.View;

/**
 *
 * @author duchu
 */
public class LedAdapter implements EieruhrListener {
    private EieruhrModel model;
    private View view;
    
    public LedAdapter(EieruhrModel model, View view){
        this.model = model;
        this.view = view;
    }
    
    public void registerEvents(){
        this.model.addEieruhrListener(this);
    }

    @Override
    public void timerAbgelaufen(TimerAbgelaufenEvent evt) {
        this.view.getLed().setEingeschaltet(true);
        this.view.getBtnStart().setEnabled(true);
        this.view.getBtnPause().setEnabled(false);
        this.view.getBtnUnpause().setEnabled(false);
        this.view.getBtnStop().setEnabled(false);
        this.view.getSpinnerHour().setEnabled(true);
        this.view.getSpinnerMinute().setEnabled(true);
        this.view.getSpinnerSecond().setEnabled(true);
    }
}
