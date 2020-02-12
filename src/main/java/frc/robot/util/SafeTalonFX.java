package frc.robot.util;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class SafeTalonFX extends WPI_TalonFX {

    private int currentLimit = 40;
    private int currentThreshold = 0;
    private double currentThresholdTime = 0; // In Milliseconds

    public SafeTalonFX(int deviceNumber){
        super(deviceNumber);

        configFactoryDefault();

        StatorCurrentLimitConfiguration config = new StatorCurrentLimitConfiguration(
            true,
            currentLimit,
            currentThreshold,
            currentThresholdTime
        );

        configStatorCurrentLimit(config);
    }
}