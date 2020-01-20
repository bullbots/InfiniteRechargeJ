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

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private WPI_TalonSRX talon;

  public Intake() {
    talon = new WPI_TalonSRX(Constants.INTAKE_PORT);
  }

  public void set(double val){
    talon.set(val);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stop(){
    talon.stopMotor();
  }
}
