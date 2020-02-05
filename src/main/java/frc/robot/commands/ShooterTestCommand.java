/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.subsystems.Shooter;


public class ShooterTestCommand extends CommandBase {
  /**
   * Creates a new ShooterTestCommand.
   */
  Shooter shooter;

  NetworkTableEntry top_motor_velocity;
  NetworkTableEntry bottom_motor_velocity;
  NetworkTableEntry top_motor_position;
  NetworkTableEntry bottom_motor_position;
  NetworkTableEntry top_motor_current;
  NetworkTableEntry bottom_motor_current;
  NetworkTableEntry input_top_motor;
  NetworkTableEntry input_bottom_motor;
  public ShooterTestCommand(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    addRequirements(this.shooter);

    top_motor_velocity = Shuffleboard.getTab("Shooter")
        .add("Top Motor Velocity", 0)
        .withSize(2,2)
        // .withPosition() Set later so there are no conflictions
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
    bottom_motor_velocity = Shuffleboard.getTab("Shooter")
        .add("Bottom Motor Velocity", 0)
        .withSize(2,2)
        // .withPosition()
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();

    top_motor_position = Shuffleboard.getTab("Shooter")
        .add("Top Motor Position", 0)
        .withSize(2,2)
        // .withPosition()
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
    bottom_motor_position = Shuffleboard.getTab("Shooter")
        .add("Bottom Motor Position", 0)
        .withSize(2,2)
        // .withPosition()
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();

    top_motor_current = Shuffleboard.getTab("Shooter")
        .add("Top Motor Current", 0)
        .withSize(2,2)
        // .withPosition()
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
    bottom_motor_current = Shuffleboard.getTab("Shooter")
        .add("Bottom Motor Current", 0)
        .withSize(2,2)
        // .withPosition()
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();

    input_top_motor = Shuffleboard.getTab("Shooter")
        .add("Top Motor Input Speed (RPM)", 0)
        .withSize(2,2)
        // .withPosition()
        .getEntry();
    input_bottom_motor = Shuffleboard.getTab("Shooter")
        .add("Bottom Motor Input Speed (RPM)", 0)
        .withSize(2,2)
        // .withPosition()
        .getEntry();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((int)input_top_motor.getNumber(0) > 5500) {
      input_top_motor.setNumber(5500);
    } else {
      input_top_motor.getNumber(0);
    }
    if ((int)input_bottom_motor.getNumber(0) > 5500) {
      input_bottom_motor.setNumber(5500);
    } else {
      input_bottom_motor.getNumber(0);
    }
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
