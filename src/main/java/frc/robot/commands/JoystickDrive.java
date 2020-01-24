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
import frc.robot.RobotContainer;

public class JoystickDrive extends CommandBase {
  
  private Drivetrain m_drivetrain;
  private DoubleSupplier joyY;
  private DoubleSupplier joyX;
  private DoubleSupplier joyZ;

  public JoystickDrive(Drivetrain drivetrain, DoubleSupplier joyY, DoubleSupplier joyX, DoubleSupplier joyZ) {
    m_drivetrain = drivetrain;
    this.joyY = joyY;
    this.joyX = joyX;
    this.joyZ = joyZ;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = -joyY.getAsDouble(); // Because Negative is forward on the joysticks
    double x = -joyX.getAsDouble();
    double z = joyZ.getAsDouble();   

    m_drivetrain.diffDrive(y, z, x);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
