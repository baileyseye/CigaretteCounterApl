package controller;

import model.Model;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Controller {
    private int dayCounter = 1;
    private Model model;
    private boolean newDay;

    public Controller(Model model) {
        this.model = model;
    }

    public boolean isNewDay() {
        newDay = true;
        dayCounter++;
        return true;
    }

    public void setCigaretteCounter(int cigaretteCounter) {
        model.setCigaretteCounter(cigaretteCounter);
    }

    public int getCigaretteCount() {
        return model.getCigaretteCounter();
    }

    public int cigaretteCounterIncr() {
        return model.cigaCounterIncr();
    }


    public void handleExit() {
        Runtime.getRuntime().exit(0);
    }

    public void writeCigaretteCounterToFile() {
        try {
            Files.createDirectories(Paths.get("src/main/resources"));
            String fileName = "cigaretteCounter.txt";
            Path filePath = Paths.get("src/main/resources", fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
                switch (getCigaretteCount()) {
                    case 21:
                        setCigaretteCounter(1);
                        isNewDay();
                        break;
                }
                String entry = String.format("Day %d: %d%n", dayCounter, getCigaretteCount());
                writer.write(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
