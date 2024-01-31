package view;

import controller.Controller;
import model.Model;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ViewTest {
   static View view;
@BeforeAll
static void init(){
    Model model = new Model();
    Controller controller = new Controller(model);
    view = new View(controller);
}

    @BeforeEach
    void setUp() {
     System.out.println("start test");
    }

    @AfterEach
    void tearDown() {
     System.out.println("stop");
    }



    @Test
    void createComponents() {

    }

    @Test
    void addElements() {

    }

    @Test
    void actionPerformed() {

    }

    @Test
    void textAreaListener() {
    }
}