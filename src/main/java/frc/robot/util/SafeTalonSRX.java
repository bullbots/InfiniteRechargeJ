package frc.robot.util;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SafeTalonSRX extends WPI_TalonSRX{

    private int currentLimit = 40;
    
    public SafeTalonSRX(int deviceNumber){
        super(deviceNumber);

        configFactoryDefault();
        configContinuousCurrentLimit(40);
    }
}