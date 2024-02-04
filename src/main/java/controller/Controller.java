package controller;

import model.Model;
import view.ViewUpdater;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller extends JFrame {
    private ViewUpdater viewUpdater;
    private int dayCounter = 1;
    //private ResourceBundle msgBundle =
    // ResourceBundle.getBundle("messages", Locale.getDefault());

    private Model model;
    private boolean isLessThenTwenty;
    private boolean newDay;
    public Controller(Model model) {
        this.model = model;
    }

    public Controller(Model model, ViewUpdater viewUpdater) {
        this.model = model;
        this.viewUpdater = viewUpdater;
    }

    public boolean isNewDay() {
        newDay = true;
        dayCounter++;
        return true;
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

    public void writeCigaretteCounterToFile(int incrementedValue) {
        String filePath = System.getProperty("user.home") + "/Desktop/cigaretteCounter.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (incrementedValue % 20 == 0 ) {
                isNewDay();
            }

            String entry = String.format("Day %d: %d%n", dayCounter, incrementedValue);
            writer.write(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
