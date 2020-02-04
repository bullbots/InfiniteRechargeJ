/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

import com.revrobotics.CANSparkMaxLowLevel;
import static frc.robot.Constants.NEO_MAX_RPM;

public class ClimbDistance extends CommandBase {
  private CANSparkMaxLowLevel canSparkMaxLowLevel;
  /**
   * Creates a new ClimbDistance.
   */
  private Climb climb;
  public ClimbDistance(Climb climb) {
    this.climb = climb;
    addRequirements(this.climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double climb = SmartDashboard.getNumber("Climb Speed", 0);

    // Min  function followed by a Max clamps the value.
    climb = Math.max(-NEO_MAX_RPM, Math.min(NEO_MAX_RPM, climb));

    this.climb.set(climb);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climb.set(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

/*

                           ______________
                         _|              |_
                        |   __             |
                        |  |__|            |
                        |                  |
                        |                  |
                        |         _________|
                        |        |______
                        |        _______|
      ||               _|        |
      ||_         ____|          |____
      | |_      _|               |____|
      |   |____|                 |  |_|
      |                          |
      |_                       _|
        |_                     |
          |_                  _|
            |_              _|
              |_      __   |
                |   _|  |_ |
                | _|      ||
                ||_       ||_
                |__|      |__|

              
  This is the running Trex from the google no Wifi game
  * It is my favoret peace of art in the robot program :) *
Made By Triston Van Wyk
*/