/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMaxLowLevel;
import static frc.robot.Constants.NEO_MAX_RPM;

public class ClimbTimed extends WaitCommand {
  private CANSparkMaxLowLevel canSparkMaxLowLevel;
  /**
   * Creates a new ClimbTimed.
   */
  private Climb climb;
  public ClimbTimed(Climb climb, double seconds) {
    super(seconds);
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
    climb.set(ControlMode.PercentOutput, 1, 1);
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


                         ______
                        /      \         
                       /________\________           
                      /         \          |   |      .
                     /   0   0   \         |___|
                    /     |_|     \        |   |      |
                    |              |       |   |      |
                  __|   |_____|    |__
                 /                    \
                /                      \
               / ____              ____ \
               | |  |              |  | |
               | |  |              |  | |
              /|_|\ |              | /|_|\
               |||  | ____________ |  |||
                    | |          | |
                    | |          | |
                    | |          | |
                    | |_         | |_
                    |___|        |___|

  This is a human (it's the best I can do without a reference)
Made by Triston Van Wyk
*/
