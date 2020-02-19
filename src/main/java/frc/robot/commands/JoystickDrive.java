/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.util.Shifter.Gear;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainFalcon;
import frc.robot.RobotContainer;

public class JoystickDrive extends CommandBase {
  
  private DrivetrainFalcon m_drivetrain;
  private DoubleSupplier joyY;
  private DoubleSupplier joyX;
  private BooleanSupplier isQuickTurn;

  private final double shiftThreshold = 9.167 / 20.833;

  public JoystickDrive(DrivetrainFalcon drivetrain, DoubleSupplier joyY, DoubleSupplier joyX, BooleanSupplier isQuickTurn) {
    m_drivetrain = drivetrain;
    this.joyY = joyY;
    this.joyX = joyX;
    this.isQuickTurn = isQuickTurn;

    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double yOut;

    if (Math.abs(joyY.getAsDouble()) < shiftThreshold || isQuickTurn.getAsBoolean()) {
      m_drivetrain.shifter.shiftLow();
      yOut = joyY.getAsDouble();
    }else{
      m_drivetrain.shifter.shiftHigh();
      yOut = (joyY.getAsDouble()*21037-36.7)/21000.;
    }

    SmartDashboard.putString("Gear", m_drivetrain.shifter.getGear().toString());

    m_drivetrain.curvatureDrive(yOut, joyX.getAsDouble()*.7, isQuickTurn.getAsBoolean());
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.set(0,0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
