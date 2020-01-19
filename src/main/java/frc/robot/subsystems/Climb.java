package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private WPI_TalonSRX climb_talon;
    
    public Climb() {
        climb_talon = new WPI_TalonSRX(Constants.CLIMB_TALON);
    }

    public void set( ControlMode control_mode, double magnitude) {
        climb_talon.set(control_mode, magnitude);
    }
}