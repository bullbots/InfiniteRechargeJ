package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.SafeTalonSRX;

public class Climb extends SubsystemBase {
    private final SafeTalonSRX climb_talon = new SafeTalonSRX(Constants.CLIMB_TALON);

    public Climb() {
        climb_talon.setNeutralMode(NeutralMode.Brake);
    }

    /** This tells the code to set the climb talon's contol mode and magnitude
     * @param control_mode This is the controm mode of the motor
     * @param magnitude This the magnitude of the motor
     */
    public void set( ControlMode control_mode, double magnitude) {
        climb_talon.set(control_mode, magnitude);
    }

    /** This tells the motor what set it's speed to
     * @param speed This is the speed of the motor
     */
    public void set(double speed) {
        climb_talon.set(speed);
    }

    /** This sets the mode, demand and demand type for the climb motors
     * @param mode This is the mode of the control mode
     * @param demand0 This is the demand 1 
     * @param demand1Type This is the type of demand for demand 2
     * @param demand1 This is demand 2
     */
    public void set(ControlMode mode, double demand0, DemandType demand1Type, double demand1) {
        climb_talon.set(mode, demand0, demand1Type, demand1);
    }
}