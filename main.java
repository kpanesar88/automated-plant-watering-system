import edu.princeton.cs.introcs.StdDraw;
import jm.music.data.Note;
import org.firmata4j.I2CDevice;
import org.firmata4j.firmata.*; // Maven import Firmata4j & SLF4j on macOS & Windows
import org.firmata4j.IODevice; // You also need to import JSSC in using Windows.
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;


public class minor {


   public static void main(String[] args) {


       String myPort = "COM3";
       IODevice myGroveBoard = new FirmataDevice(myPort);


       try {
           myGroveBoard.start();
           System.out.println("Board started.");
           myGroveBoard.ensureInitializationIsDone();


           I2CDevice i2cObject = myGroveBoard.getI2CDevice((byte) 0x3C);
           SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
           theOledObject.init();


           Pin moistPin = myGroveBoard.getPin(14);
           moistPin.setMode(Mode.ANALOG);


           Pin waterPump = myGroveBoard.getPin(2);
           waterPump.setMode(Mode.OUTPUT);


           long startTime = System.currentTimeMillis();
           ArrayList<Double> moisture = new ArrayList<Double>(1000);


           StdDraw.setCanvasSize(1000, 600);
           StdDraw.setPenRadius(0.005);
           StdDraw.setPenColor(StdDraw.BLUE);
           StdDraw.setXscale(-3, 100);
           StdDraw.setYscale(-1, 6);


           StdDraw.line(0, 0, 0, 5);
           StdDraw.line(0, 0, 100, 0);


           StdDraw.text(50, -0.5, "Time (ms)");
           StdDraw.text(-4, 3, "[V]");
           StdDraw.text(50, 5.5, "Moisture vs Time");


           for (;;) {
               long moistValue = moistPin.getValue();
               double voltage = (moistValue / 1023.0) * 5.0; // Convert the raw reading to a voltage between 0-5V
               double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
               System.out.println("Time: " + elapsedTime + ", Moisture Voltage: " + voltage);


               moisture.add(voltage); // Add the voltage reading to the ArrayList
               float voltageFloat = (float) voltage;
               theOledObject.getCanvas().drawString(0, 50, "Moisture:");
               theOledObject.getCanvas().drawString(60, 50, String.valueOf(voltageFloat));


               theOledObject.display();


               StdDraw.point(elapsedTime, voltage);


               if (voltage > 3.2) {
                   theOledObject.getCanvas().drawString(0, 0, "A Lot Of Water");
                   theOledObject.display();
                   System.out.println("A Lot Of Water");
                   waterPump.setValue(1);
                   Thread.sleep(2000);
                   waterPump.setValue(0);
                   theOledObject.clear();


               } else if (voltage > 2.5 && voltage < 3.2) {
                   theOledObject.getCanvas().drawString(0, 0, "A Little Bit More");
                   theOledObject.display();
                   System.out.println("A little bit more");
                   waterPump.setValue(0);
                   waterPump.setValue(1);
                   Thread.sleep(1000);
                   waterPump.setValue(0);
                   theOledObject.clear();


               }else if (voltage < 2.5){
                   theOledObject.getCanvas().drawString(0, 0, "Enough");
                   theOledObject.display();
                   System.out.println("Enough");
                   waterPump.setValue(0);
                   Thread.sleep(1000);
                   waterPump.setValue(0);
                   theOledObject.clear();


               }
           }
       }
       catch (Exception ex) {
           System.out.println("couldn't connect to board."); // message if the connection didn’t happen.
       }
   }
}



