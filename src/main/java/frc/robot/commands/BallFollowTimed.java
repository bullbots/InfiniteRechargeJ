/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DrivetrainFalcon;

public class BallFollowTimed extends WaitCommand {
  /**
   * Creates a new ShootTimed.
   */
  private DrivetrainFalcon drivetrainFalcon;

  private double P = 1; // Still needs tuned
  private double error_from_center;  // Will be a NetworkTable...

  public BallFollowTimed(double seconds) {
    super(seconds);
    
    this.drivetrainFalcon = drivetrainFalcon;

    addRequirements(drivetrainFalcon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      drivetrainFalcon.arcadeDrive(.3, P * error_from_center); // Multiply by position of ball from center
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      drivetrainFalcon.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}