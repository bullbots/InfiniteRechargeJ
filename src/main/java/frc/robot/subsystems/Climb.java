package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private WPI_TalonSRX climb_talon;
    
    public Climb() {
        climb_talon = new WPI_TalonSRX(Constants.CLIMB_TALON);
    }

    public void set( ControlMode control_mode, double magnitude) {
        climb_talon.set(control_mode, magnitude);
    }

    public void set(double speed) {
        climb_talon.set(speed);
    }

    public void set(ControlMode mode, double demand0, DemandType demand1Type, double demand1) {
        climb_talon.set(mode, demand0, demand1Type, demand1);
    }
}