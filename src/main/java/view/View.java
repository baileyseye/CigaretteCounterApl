package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class View extends JFrame implements ActionListener, ViewUpdater {
    private JTextArea textArea;
    private JButton jButton;
    private JButton quitButton;
    private JLabel info;
    private Controller controller;
    ViewUpdater viewUpdater;
    private ResourceBundle msgBundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    String infoText = msgBundle.getString("infoLabelText");


    public View(Controller controller) {
        super("Антикурение");
        this.controller = controller;
        this.viewUpdater = this;
    }


    public void init(int width, int height, boolean isVisible) {
        SwingUtilities.invokeLater(() -> {
            setSize(width, height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(isVisible);
            createComponentsOnStart();
            addElements();
        });
    }

    //methods
    public void createComponentsOnStart() {
        createTextArea();
        createJButtons();
        createInfo();
        setLayout();
    }

    public void createInfo() {
        info = new JLabel(infoText);
    }

    public void createJButtons() {
        jButton = new JButton();
        jButton.setText(msgBundle.getString("smokeButtonText"));
        jButton.addActionListener(this);
        quitButton = new JButton();
        quitButton.setText(msgBundle.getString("quitButtonText"));
        quitButton.addActionListener(e -> controller.handleExit());
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setText("Одумайся, еще не поздно бросить курить \n сегодня. " +
                "Первая сигарета - самая тяжелая.");
    }

    public void setLayout() {
        this.setLayout(new BorderLayout());
    }

    public void addElements() {
        add(textArea, BorderLayout.CENTER);
        add(jButton, BorderLayout.SOUTH);
        add(info, BorderLayout.NORTH);
        add(quitButton, BorderLayout.EAST);
    }

    //Override zone
    @Override
    public void actionPerformed(ActionEvent e) {
        int incrementedValue = controller.cigaCounterIncr();
        viewUpdater.updateInfo(msgBundle.getString("infoLabelText") + incrementedValue);
        if (incrementedValue < 3) {
            viewUpdater.updateTextArea(msgBundle.getString("goodText"));
            viewUpdater.updateButtonText(msgBundle.getString("smokeButtonText"));
        } else {
            viewUpdater.updateTextArea(msgBundle.getString("hopelessText"));
        }

    }





        @Override
        public void updateInfo(String text) {
            info.setText(text);
        }

        @Override
        public void updateTextArea(String text) {
            textArea.setText(text);
        }

        @Override
        public void updateButtonText(String text) {
            jButton.setText(text);
        }
}