/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.SafeTalonSRX;

public class ControlPanel extends SubsystemBase {

  private SafeTalonSRX control_motor = new SafeTalonSRX(Constants.CONTROL_PORT);

  private DoubleSolenoid control_solenoid = new DoubleSolenoid(Constants.BRAKE_ON_CHANNEL, Constants.BRAKE_OFF_CHANNEL);

  private NetworkTableEntry controlVelocity;

  public ControlPanel() {
    brake();
    configureShuffleBoard();

    control_motor = new SafeTalonSRX(Constants.CONTROL_PORT);
  }

  /** This sets the intake motor
   * @param val This is a Value that sets the motor
   */
  public void setcontrol(double val){
    control_motor.set(val);
  }

  public void set(double speed) {
  control_motor.set(speed);
}
  public void brake() {
    control_solenoid.set(Value.kForward);
  }

  public void unbrake() {
    control_solenoid.set(Value.kReverse);
  }

  private void configureShuffleBoard() {
    controlVelocity = Shuffleboard.getTab("Control")
                  .add("Intake Encoder Velocity", 0)
                  .withSize(2, 2)
                  .withSize(0, 2)
                  .withWidget(BuiltInWidgets.kGraph)
                  .getEntry();
  }
  @Override
  public void periodic() {
  }

  public void stop(){
    control_motor.stopMotor();
  }
}
