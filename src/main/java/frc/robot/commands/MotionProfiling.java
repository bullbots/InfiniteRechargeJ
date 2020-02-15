/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainFalcon;

public class MotionProfiling extends CommandBase {
  /**
   * Creates a new MotionProfiling.
   */
  private double motion = 1;          //we will have to change this later 
  private DrivetrainFalcon drivetrainFalcon;
  public MotionProfiling() {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrainFalcon = drivetrainFalcon;


    addRequirements(this.drivetrainFalcon);
  }

  public boolean usestate(boolean motionprofiling) {
  if (motion > 1)
    return true;
  else return false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrainFalcon.set(ControlMode.MotionMagic, 1, 1); // this probably isn't what we want but we can change it later.
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainFalcon.set(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
