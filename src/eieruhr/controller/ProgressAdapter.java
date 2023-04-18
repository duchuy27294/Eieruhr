/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eieruhr.controller;

import eieruhr.model.EieruhrModel;
import eieruhr.view.View;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

/**
 *
 * @author duchu
 */
public class ProgressAdapter implements Subscriber<Integer>{
    private EieruhrModel model;
    private View view;
    private Flow.Subscription subscription;
    
    public ProgressAdapter(EieruhrModel model, View view){
        this.model = model;
        this.view = view;
        this.subscription = null;
        this.view.getPb().setStringPainted(true);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        int value = (int)((1 - ((double)item/(double)this.model.getTotalTime()))*100);
        this.view.getPb().setValue(value);
        this.view.getPb().setString(String.valueOf(value) + " %");
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("FERTIG");
    }
    
}
