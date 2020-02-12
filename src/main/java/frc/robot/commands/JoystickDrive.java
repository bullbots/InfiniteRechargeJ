/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainFalcon;
import frc.robot.RobotContainer;

public class JoystickDrive extends CommandBase {
  
  private DrivetrainFalcon m_drivetrain;
  private DoubleSupplier joyY;
  private DoubleSupplier joyX;

  public JoystickDrive(DrivetrainFalcon drivetrain, DoubleSupplier joyY, DoubleSupplier joyX) {
    m_drivetrain = drivetrain;
    this.joyY = joyY;
    this.joyX = joyX;

    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    System.out.println(joyY.getAsDouble());
    m_drivetrain.arcadeDrive(joyY.getAsDouble(), joyX.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
