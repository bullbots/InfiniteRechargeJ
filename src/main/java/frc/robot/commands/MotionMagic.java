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

  public MotionMagic(Drivetrain drivetrain, int targetDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    targetDistance = 5; // This number is the distance it will travel and can be changed
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.drivetrain.set(ControlMode.MotionMagic, targetDistance, targetDistance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.drivetrain.set(ControlMode.MotionMagic, 0, 0); // Is this line needed? Shouldn't the motors just turn off when it reaches the targetDistance?
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double[] position = this.drivetrain.getPosition();
    if (position[0] == position[1])
      currentPosition = position[0]; // There might be a cleaner way to do this, I did this because the if statement doesn't work with an array
    if (currentPosition == targetDistance) {
      return true;
    } else {
      return false;
    }
  }
}
