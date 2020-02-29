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

  private final double shiftThreshold = 9.167 / 20.833;

  public JoystickDrive(DrivetrainFalcon drivetrain, DoubleSupplier joyY, DoubleSupplier joyX) {
    m_drivetrain = drivetrain;
    this.joyY = joyY;
    this.joyX = joyX;

    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    boolean isQuickTurn = (Math.abs(this.joyY.getAsDouble()) < .2 && m_drivetrain.getVelocities()[0] < 1000);  

    if (Math.abs(joyY.getAsDouble()) > shiftThreshold) {
      m_drivetrain.shifter.shiftHigh();
    }else{
      m_drivetrain.shifter.shiftLow();
    }

    SmartDashboard.putString("Gear", m_drivetrain.shifter.getGear().toString());

    m_drivetrain.curvatureDrive(joyY.getAsDouble(), joyX.getAsDouble(), isQuickTurn);
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
