/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clock;

import java.util.EventListener;

/**
 *
 * @author duchu
 */
public interface ClockListener extends EventListener{
    public void timeChanged(ClockEvent evt);
}
