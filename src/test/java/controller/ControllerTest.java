package controller;

import model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Model model;
    private Controller controller;

    @BeforeEach
    void setUp() {
        model = new Model();
        controller = new Controller(model);
    }

    @Test
    void isNewDay() {
        assertTrue(controller.isNewDay());
        assertEquals(0, controller.getCigaretteCount());
    }

    @Test
    void setCigaretteCounter() {
        controller.setCigaretteCounter(5);
        assertEquals(5, controller.getCigaretteCount());
    }

    @Test
    void cigaretteCounterIncr() {
        assertEquals(0, controller.cigaretteCounterIncr());
        assertEquals(1, controller.getCigaretteCount());
    }

    @Test
    void handleExit() {
        assertDoesNotThrow(() -> controller.handleExit());
    }

    @Test
    void writeCigaretteCounterToFile() {
        assertDoesNotThrow(() -> controller.writeCigaretteCounterToFile());
    }
}