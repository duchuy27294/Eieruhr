/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package eieruhr.view;

import eieruhr.model.EieruhrModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author duchu
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        this.spinnerHour.setModel(new SpinnerNumberModel(0,0,23,1));
        this.spinnerMinute.setModel(new SpinnerNumberModel(0,0,59,1));
        this.spinnerSecond.setModel(new SpinnerNumberModel(0,0,69,1));
        this.model = null;
    }
    
    public void setModel(EieruhrModel model){
        this.model = model;
        this.getClock().setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSetTime = new javax.swing.JPanel();
        pnHour = new javax.swing.JPanel();
        lblHour = new javax.swing.JLabel();
        spinnerHour = new javax.swing.JSpinner();
        pnMinute = new javax.swing.JPanel();
        lblMinute = new javax.swing.JLabel();
        spinnerMinute = new javax.swing.JSpinner();
        pnSecond = new javax.swing.JPanel();
        lblSecond = new javax.swing.JLabel();
        spinnerSecond = new javax.swing.JSpinner();
        btnSet = new javax.swing.JButton();
        pnClock = new javax.swing.JPanel();
        clock = new beans.digitalclock.DigitalClock();
        pnLed = new javax.swing.JPanel();
        led = new beans.led.Led();
        lblAbgelaufen = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();
        pnButtons = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnUnpause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        pnHour.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHour.setText("Stunden");
        pnHour.add(lblHour);
        pnHour.add(spinnerHour);

        pnSetTime.add(pnHour);

        pnMinute.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMinute.setText("Minuten");
        pnMinute.add(lblMinute);
        pnMinute.add(spinnerMinute);

        pnSetTime.add(pnMinute);

        pnSecond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSecond.setText("Sekunden");
        pnSecond.add(lblSecond);
        pnSecond.add(spinnerSecond);

        pnSetTime.add(pnSecond);

        btnSet.setText("Setzen");
        pnSetTime.add(btnSet);

        getContentPane().add(pnSetTime);

        pnClock.setLayout(new javax.swing.BoxLayout(pnClock, javax.swing.BoxLayout.Y_AXIS));
        pnClock.add(clock);

        pnLed.setLayout(new java.awt.GridLayout());

        led.setEingeschaltet(false);
        pnLed.add(led);

        lblAbgelaufen.setText("Timer Abgelaufen");
        pnLed.add(lblAbgelaufen);
        pnLed.add(pb);

        pnClock.add(pnLed);

        getContentPane().add(pnClock);

        btnStart.setText("Starten");
        pnButtons.add(btnStart);

        btnPause.setText("Pausieren");
        pnButtons.add(btnPause);

        btnUnpause.setText("Fortfahren");
        pnButtons.add(btnUnpause);

        btnStop.setText("Stoppen");
        pnButtons.add(btnStop);

        getContentPane().add(pnButtons);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnSet;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnUnpause;
    private beans.digitalclock.DigitalClock clock;
    private javax.swing.JLabel lblAbgelaufen;
    private javax.swing.JLabel lblHour;
    private javax.swing.JLabel lblMinute;
    private javax.swing.JLabel lblSecond;
    private beans.led.Led led;
    private javax.swing.JProgressBar pb;
    private javax.swing.JPanel pnButtons;
    private javax.swing.JPanel pnClock;
    private javax.swing.JPanel pnHour;
    private javax.swing.JPanel pnLed;
    private javax.swing.JPanel pnMinute;
    private javax.swing.JPanel pnSecond;
    private javax.swing.JPanel pnSetTime;
    private javax.swing.JSpinner spinnerHour;
    private javax.swing.JSpinner spinnerMinute;
    private javax.swing.JSpinner spinnerSecond;
    // End of variables declaration//GEN-END:variables
    private EieruhrModel model;

    /**
     * @return the btnPause
     */
    public javax.swing.JButton getBtnPause() {
        return btnPause;
    }

    /**
     * @return the btnSet
     */
    public javax.swing.JButton getBtnSet() {
        return btnSet;
    }

    /**
     * @return the btnStart
     */
    public javax.swing.JButton getBtnStart() {
        return btnStart;
    }

    /**
     * @return the btnStop
     */
    public javax.swing.JButton getBtnStop() {
        return btnStop;
    }

    /**
     * @return the clock
     */
    public beans.digitalclock.DigitalClock getClock() {
        return clock;
    }

    /**
     * @return the led
     */
    public beans.led.Led getLed() {
        return led;
    }

    /**
     * @return the spinnerHour
     */
    public javax.swing.JSpinner getSpinnerHour() {
        return spinnerHour;
    }

    /**
     * @return the spinnerMinute
     */
    public javax.swing.JSpinner getSpinnerMinute() {
        return spinnerMinute;
    }

    /**
     * @return the spinnerSecond
     */
    public javax.swing.JSpinner getSpinnerSecond() {
        return spinnerSecond;
    }

    /**
     * @return the pb
     */
    public javax.swing.JProgressBar getPb() {
        return pb;
    }

    /**
     * @return the btnUnpause
     */
    public javax.swing.JButton getBtnUnpause() {
        return btnUnpause;
    }

}
