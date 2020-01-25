/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.subsystems.Drivetrain;

public class MotionMagic extends CommandBase {
  /**
   * Creates a new MotionMagic.
   */
  private Drivetrain drivetrain;
  private int targetDistance;
  private double currentPosition;

  private int allowedError = 100;

  public MotionMagic(Drivetrain drivetrain, int targetDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.targetDistance = targetDistance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.set(ControlMode.MotionMagic, targetDistance, targetDistance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double[] position = this.drivetrain.getPosition();
    if (position[0] == position[1])
      currentPosition = position[0];
    if (Math.abs(currentPosition - targetDistance) == allowedError) {
      return true;
    } else {
      return false;
    }
  }
}
