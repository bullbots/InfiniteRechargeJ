/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class AlignShooter extends PIDCommand {

  public AlignShooter(PIDController controller, DoubleSupplier measurementSource, double setpoint, DoubleConsumer useOutput, Subsystem... requirements) {
    super(controller, measurementSource, setpoint, useOutput, requirements);
    controller.setTolerance(50, 50);
  }

  @Override
  public boolean isFinished() {
    return m_controller.atSetpoint();
  }
}
