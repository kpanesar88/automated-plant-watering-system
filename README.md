# Automated Plant Watering System

This Java application reads moisture levels from a sensor and controls a water pump based on those readings. It also displays the moisture level on an OLED screen and plots the data over time using `StdDraw`.

## Features

- **Moisture Sensing**: Continuously reads moisture levels from a connected sensor.
- **Water Pump Control**: Activates a water pump based on moisture levels.
- **Data Visualization**: Plots moisture levels over time using `StdDraw`.
- **OLED Display**: Shows real-time moisture levels and status messages on an OLED display.

## Requirements

- **Java**: Ensure you have Java installed on your system.
- **Libraries**:
  - **Firmata4j**: For communication with the Arduino board.
  - **StdDraw**: For plotting the moisture levels.
  - **JSSC**: For serial communication (Windows users).
- **Hardware**:
  - Arduino board
  - Moisture sensor
  - Water pump
  - OLED display


2. **Connect Hardware**:
   - Connect the moisture sensor to the analog pin (Pin 14).
   - Connect the water pump to the digital output pin (Pin 2).
   - Connect the OLED display via I2C.

3. **Configure the Port**:
   - Update the `myPort` variable in the code with the correct port name for your Arduino board (e.g., `COM3` for Windows or `/dev/ttyUSB0` for Linux).
   - Update the Hardware pins or addresses for your Arduino Circuit/Board.

## Troubleshooting
   - **Connection Issues**: Ensure the correct port is specified and that all hardware is properly connected.
   - **Library Issues**: Verify that all required libraries are included in the classpath.

## Resources
  - Grove Beginner Kit Manual: [User Manual](https://files.seeedstudio.com/wiki/Grove-Beginner-Kit-For-Arduino/res/Grove-Beginner-Kit-For-ArduinoPDF.pdf)
  - Firmata Intro: [Intro](https://www.yorku.ca/professor/drsmith/2022/02/25/easy-java-arduino-with-firmata/)
  - I2C & Firmata Example: [Example](https://www.yorku.ca/professor/drsmith/2024/06/11/firmata-example-i2c-sensor-java-firmata4j/)
