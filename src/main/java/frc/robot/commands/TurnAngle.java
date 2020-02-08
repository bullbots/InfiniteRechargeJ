/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class TurnAngle extends CommandBase {
  /**
   * Creates a new TurnAngle.
   */

  private Drivetrain drivetrain;
  private Gyro gyro;

  private double currentAngle;
  private int targetAngle;
  private int allowedError = 100;
  private float turnSpeed = .5f;
  private String direction;

  public TurnAngle(Drivetrain drivetrain, Gyro gyro, String direction, int targetAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.gyro = gyro;

    gyro.calibrate();  // makes sure Gyro is at zero on start.

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currentAngle = gyro.getAngle();
    gyro.reset();

    /* I'm sure there is a better way of doing this other than using Strings
    I'm also not sure if we even need this */
    if (direction == "LEFT") {
      drivetrain.set(ControlMode.PercentOutput, -turnSpeed, turnSpeed);
    } else if (direction == "RIGHT") {
      drivetrain.set(ControlMode.PercentOutput, turnSpeed, -turnSpeed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(currentAngle - targetAngle) <= allowedError;
  }
}
