package controller;

import model.Model;
import view.ViewUpdater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;


public class Controller {

    private ViewUpdater viewUpdater;
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
        System.exit(0);
    }

    public void writeCigaretteCounterToFile() {
        String resourcePath = "src/main/resources/cigaretteCounter.txt";
        File resourceFile = new File(resourcePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resourceFile, true))) {
            switch (getCigaretteCount()) {
                case 21:
                    setCigaretteCounter(1);
                    isNewDay();
                    break;
            }
            String entry = String.format("Day %d: %d%n", dayCounter, getCigaretteCount());
            writer.write(entry);
            copyFileToResources(resourcePath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void copyFileToResources(String resourcePath) throws IOException, URISyntaxException {
        try {
            Path sourcePath = new File(resourcePath).toPath();
            Path destinationPath = new File("target/classes/cigaretteCounter.txt").toPath();
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
