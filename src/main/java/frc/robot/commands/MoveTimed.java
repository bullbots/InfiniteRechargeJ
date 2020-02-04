/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public class MoveTimed extends WaitCommand {
  private Drivetrain drivetrain;
  /**
   * Creates a new MoveTimed.
   */
  public MoveTimed(Drivetrain drivetrain, double seconds) {
    super(seconds);
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;


    addRequirements(this.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.drivetrain.set(ControlMode.PercentOutput, 1, 1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}


/*



                     __
                    / /
                   / /
                  / /
                 | |
                 | |
         ________|_|________
        /                   \
       /  _               _  \
      /  |_|            _|_|_ \
     |  |___|          |_|_|_| |
     |   |_|             |_|   |
     |        _________        |
      \      /         \      /
       \    /           \    /
        \__/             \__/


    This is a gameing controler. It isn't the bset but my friend wanted me to make one
  Made By Triston Van Wyk
*/