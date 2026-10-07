package edu.spu.se411.lab08;

import org.slf4j.LoggerFactory;

public class Logger implements Observer {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Override
    public void update(Subject subject) {

        if (subject instanceof Sensor) {

            Sensor sensor = (Sensor) subject;

            System.out.printf("Logger: %s reading changed to %.2f%n", sensor.getName(), sensor.getReading());

            logger.info("{} reading changed to {}", sensor.getName(), sensor.getReading());
        }
    }
}