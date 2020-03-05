/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */

   private Shooter shooter;
   private double vel = 0;
   private BooleanSupplier isLongShot;

  public Shoot(Shooter shooter, BooleanSupplier isLongShot) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.isLongShot = isLongShot;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (isLongShot.getAsBoolean()) {
      vel = 6500;
      shooter.raiseSolenoid();
    }else {
      vel = 3000;
      shooter.lowerSolenoid();
    }
    System.out.println(vel);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.set(vel, -vel);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.set(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
