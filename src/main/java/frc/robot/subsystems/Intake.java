/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.util.SafeTalonSRX;
import frc.robot.util.SafeVictorSPX;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private SafeVictorSPX intake_motor;

  public Intake() {
    configureShuffleBoard();

    intake_motor = new SafeVictorSPX(Constants.INTAKE_PORT);
  }

  /** This sets teh intake motor
   * @param val This is a value that sets the motor
   */
  public void setintake(VictorSPXControlMode mode, double val){
    intake_motor.set(mode, val);
  }
/**This sets the value of the intake wheel
 * @param val
 */

  private void configureShuffleBoard() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // intakeVelocity.setDouble(talon.getSelectedSensorVelocity());
  }

  public void stop(){
    intake_motor.stopMotor();
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