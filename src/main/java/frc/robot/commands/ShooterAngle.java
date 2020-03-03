/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.util.Shifter;

import static frc.robot.Constants.NEO_MAX_RPM;

public class ShooterAngle extends CommandBase {
  /**
   * Creates a new ShooterAngle.
   */
  private Shooter shooter;
  public ShooterAngle(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(this.shooter);

  public final Shifter shifter = new Shifter(Constants.LOW_GEAR_CHANNEL, Constants.HIGH_GEAR_CHANNEL);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.set(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
