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
  private WPI_TalonSRX talon;

  private NetworkTableEntry intakeVelocity;

  public Intake() {
    talon = new WPI_TalonSRX(Constants.INTAKE_PORT);

    configureShuffleBoard();
  }

  public void set(double val){
    talon.set(val);
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
    intakeVelocity.setDouble(talon.getSelectedSensorVelocity());
  }

  public void stop(){
    talon.stopMotor();
  }
}
