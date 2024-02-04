package org.example;

import controller.Controller;
import model.Model;
import view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);
        SwingUtilities.invokeLater(() -> {
            view.init(440, 500, true);
        });
    }
}