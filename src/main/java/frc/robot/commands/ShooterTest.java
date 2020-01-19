/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterTest extends CommandBase {
  /**
   * Creates a new ShooterTest.
   */
  private Shooter shooter;
  public ShooterTest(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(this.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.set(0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    int top = (int) SmartDashboard.getNumber("Top Speed", 0);
    int bottom = (int) SmartDashboard.getNumber("Bottom Speed", 0);

    top = top > 5600? 5600: top;
    top = top < -5600? -5600: top;

    bottom = bottom > 5600? 5600: bottom;
    bottom = bottom < -5600? -5600: bottom;

    shooter.set(top, bottom);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
