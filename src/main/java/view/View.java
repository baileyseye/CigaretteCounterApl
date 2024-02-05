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
        super("AntiSmoking");
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
        jButton.setText(msgBundle.getString("smokeButtonInitText"));
        jButton.addActionListener(this);
        quitButton = new JButton();
        quitButton.setText(msgBundle.getString("quitButtonText"));
        quitButton.addActionListener(e -> controller.handleExit());
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setText("Come to your senses, it's not too late to quit smoking \n today. " +
                "The first cigarette is the hardest.");
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
        int incrementedValue = controller.cigaretteCounterIncr();
        controller.writeCigaretteCounterToFile();
        viewUpdater.updateInfo(msgBundle.getString("infoLabelText") + incrementedValue);
        switch (incrementedValue) {
            case 0:
                viewUpdater.updateTextArea(msgBundle.getString("are_you_sure?"));
                viewUpdater.updateButtonText(msgBundle.getString("yes"));
                break;
            case 1:
                viewUpdater.updateTextArea(msgBundle.getString("firstCigarette"));
                break;
            case 2:
                viewUpdater.updateTextArea(msgBundle.getString("secondCigarette"));
                break;
            case 3:
                viewUpdater.updateTextArea(msgBundle.getString("warnText"));
                viewUpdater.updateButtonText(msgBundle.getString("smokeButtonTextUpd"));
                break;
            case 5:
                viewUpdater.updateTextArea(msgBundle.getString("hopelessText"));
                break;
            case 9:
                viewUpdater.updateTextArea(msgBundle.getString("hopeText"));
                break;
            case 10:
                viewUpdater.updateTextArea(msgBundle.getString("half_of_all"));
                break;
            case 11:
                viewUpdater.updateTextArea(msgBundle.getString("remaining_half"));
                break;
            case 20:
                viewUpdater.updateTextArea(msgBundle.getString("done_for_today"));
                break;
            default:
                break;
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


    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getjButton() {
        return jButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JLabel getInfo() {
        return info;
    }
}