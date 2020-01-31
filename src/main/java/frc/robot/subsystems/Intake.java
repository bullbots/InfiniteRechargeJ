/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private WPI_TalonSRX intake_motor;
  private WPI_TalonSRX wheel_motor;


  private NetworkTableEntry intakeVelocity;

  public Intake() {
    configureShuffleBoard();

    intake_motor = new WPI_TalonSRX(Constants.INTAKE_PORT);
    wheel_motor = new WPI_TalonSRX(Constants.INTAKE_Wheel_PORT);
  }

  /** This sets teh intake motor
   * @param val This is a value that sets the motor
   */
  public void setintake(double val){
    intake_motor.set(val);
  }
/**This sets the value of the intake wheel
 * @param val
 */
  public void setwheel(double val){
    wheel_motor.set(val);
  }

  private void configureShuffleBoard() {
    intakeVelocity = Shuffleboard.getTab("Diagnostics")
                .add("Intake Encoder Velocity", 0)
                .withSize(2, 2)
                .withPosition(0, 2)
                .withWidget(BuiltInWidgets.kGraph)
                .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // intakeVelocity.setDouble(talon.getSelectedSensorVelocity());
  }

  public void stop(){
    intake_motor.stopMotor();
    wheel_motor.stopMotor();
  }
}

/*
This is a car


    |______|
    | |  | |
      |  |
    |_|__|_|
    |      |


    I really like it :)
    Made by Triston Van Wyk
    */