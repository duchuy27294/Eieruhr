/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package beans.digitalclock;

import java.util.EventListener;

/**
 *
 * @author duchu
 */
public interface DigitalClockListener extends EventListener {
    public void timeChanged(DigitalClockEvent evt);
}
