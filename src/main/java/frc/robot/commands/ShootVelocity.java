/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


public class ShootVelocity extends CommandBase {
  /**
   * Creates a new ShootVelocity.
   */
  private Shooter shooter;

  double topVelocity;
  double bottomVelocity;

  public ShootVelocity(Shooter shooter, double topVelocity, double bottomVelocity) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;

    this.topVelocity = topVelocity;
    this.bottomVelocity = bottomVelocity;

    addRequirements(this.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.set(topVelocity, bottomVelocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
