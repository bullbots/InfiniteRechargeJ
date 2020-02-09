/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.NEO_MAX_RPM;

public class ControlPanelSpin extends CommandBase {
  /**
   * Creates a new ControlPanelSpin.
   */
  private ControlPanel controlpanel;
  public ControlPanelSpin(ControlPanel controlpanel) {
    this.controlpanel = controlpanel;
    addRequirements(this.controlpanel);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double control = SmartDashboard.getNumber("Control Motor Speed", 0);

    control =Math.max(-NEO_MAX_RPM, Math.min(NEO_MAX_RPM, control));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlpanel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}