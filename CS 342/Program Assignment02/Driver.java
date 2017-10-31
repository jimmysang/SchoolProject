package datastructure;

import datastructure.queue.*;
import datastructure.sample_project.RPNCalc;
import datastructure.stack.ArStack;
import datastructure.stack.LLStack;
import datastructure.stack.MyStack;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sang on 2017/10/10.
 */
public class Driver {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.doIt();
    }

    public void doIt(){
        Airport airport = new Airport(2, 2, 3, 4, 4);
        airport.run(600);
    }
}
