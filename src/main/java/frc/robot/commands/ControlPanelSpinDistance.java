/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.subsystems.ControlPanel;

public class ControlPanelSpinDistance extends CommandBase {
  /**
   * Creates a new ControlPanelSpinDistance.
   */
  private ControlPanel controlpanel;
  private int targetDistance;
  private double currentPosition;

  private int allowedError = 100;

  public ControlPanelSpinDistance(ControlPanel controlpanel, int targetDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlpanel = controlpanel;
    this.targetDistance = targetDistance;

    addRequirements(controlpanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controlpanel.set(targetDistance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double curVal = 0;
    controlpanel.set(curVal);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlpanel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double position = this.controlpanel.getVelocity();
    if (position == position)
      currentPosition = position;
    if (Math.abs(currentPosition - targetDistance) <= allowedError){
      return true;
    }else{
    return false;
    }
  }
}

/*

                              |\
                              | \
                              |  \
                              |   \
                              |    \
                              |     \
                              |      \
                              |       \
                              |        \
                              |         \
                              |          \
                              |           \
                              |            \
                              |             \
                              |              \
                              |               \
                              |                \
                              |                 \ 
                              |                  \
                              |                   \
                              |                    \
                              |                     \
                              |                      \
                              |                       \
                              |                        \
                              |_________________________\
                              |
                              |
                              |
                              |
                              |
                              |
                              |
                              |
                              |
        _________________________________________________
       \                                                /
        \            ___                 ___           /
         \          |   |               |   |         /
          \         |___|               |___|        /
           \                                        /
            \______________________________________/




  Row, row, row your boat 
  Gently through the code
  Merrily merrily, merrily, merrily
  Coding can get boring so I am making art for you :)
    * Sorry it may be alitle out of tune.*
Made by Triston Van Wyk

*/
