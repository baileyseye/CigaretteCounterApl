package view;

import controller.Controller;
import model.Model;
import org.junit.jupiter.api.*;
import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ViewTest {
   private View view;
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller(new Model());
        view = new View(controller);

        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            view.init(440, 500, false);
            latch.countDown();
        });

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Timeout while waiting for initialization");
        }
    }


}