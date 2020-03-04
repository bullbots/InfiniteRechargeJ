/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ControlDown extends CommandBase {
  /**
   * Creates a new ControlDown.
   */
  private ControlPanel controlPanel;
  private DoubleSupplier joyY;
  private BooleanSupplier brake;

  public ControlDown(ControlPanel controlPanel, DoubleSupplier joyY, BooleanSupplier brake) {
    this.controlPanel = controlPanel;
    this.joyY = joyY;
    this.brake = brake;
    addRequirements(controlPanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Output", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanel.set(joyY.getAsDouble());
    if(brake.getAsBoolean()){
      controlPanel.unbrake();
    }else {
      controlPanel.unbrake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanel.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
