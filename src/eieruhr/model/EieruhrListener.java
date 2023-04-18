/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eieruhr.model;

import java.util.EventListener;

/**
 *
 * @author duchu
 */
public interface EieruhrListener extends EventListener{
    public void timerAbgelaufen(TimerAbgelaufenEvent evt);
}
