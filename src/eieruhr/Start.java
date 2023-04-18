/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eieruhr;

import eieruhr.controller.Controller;
import eieruhr.controller.LedAdapter;
import eieruhr.controller.ProgressAdapter;
import eieruhr.model.EieruhrModel;
import eieruhr.view.View;

/**
 *
 * @author duchu
 */
public class Start {

    public Start(){
        EieruhrModel model = new EieruhrModel();
        View view = new View();
        view.setModel(model);
        Controller controller = new Controller(model,view);
        controller.registerEvents();
        LedAdapter adapter = new LedAdapter(model,view);
        adapter.registerEvents();
        ProgressAdapter pAdapter = new ProgressAdapter(model,view);
        model.addSubscription(pAdapter);
        view.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Start();
    }
    
}
