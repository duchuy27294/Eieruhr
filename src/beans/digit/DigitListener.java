/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package beans.digit;

import java.util.EventListener;

/**
 *
 * @author duchu
 */
public interface DigitListener extends EventListener {
    public void valueChanged(DigitEvent evt);
}
