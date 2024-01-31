package controller;

import model.Model;
import view.ViewUpdater;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller extends JFrame implements ActionListener {
    private ViewUpdater viewUpdater;
    private ResourceBundle msgBundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    private Model model;
    private boolean isLessThenTwenty;

    public Controller(Model model) {
        this.model = model;
    }

    public Controller(Model model, ViewUpdater viewUpdater) {
        this.model = model;
        this.viewUpdater = viewUpdater;
    }


    public void setCigaCounter(int cigaCounter) {
        model.setCigaCounter(cigaCounter);
    }

    public int getCigaCount() {
        return model.getCigaCounter();
    }

    public int cigaCounterIncr() {
        return model.cigaCounterIncr();
    }



    public void handleExit() {
        System.exit(0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int incrementedValue = model.cigaCounterIncr();
        viewUpdater.updateInfo(msgBundle.getString("infoLabelText") + incrementedValue);
        if (incrementedValue < 3) {
            viewUpdater.updateTextArea(msgBundle.getString("goodText"));
        } else {
            viewUpdater.updateTextArea(msgBundle.getString("hopelessText"));
            handleExit();
        }
        viewUpdater.updateButtonText(msgBundle.getString("smokeButtonText"));

    }
}
