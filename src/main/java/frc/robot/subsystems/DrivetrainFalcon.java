/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.util.SafeTalonFX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainFalcon extends SubsystemBase {

  private final SafeTalonFX leftMasterFalcon = new SafeTalonFX(Constants.LEFT_MASTER_PORT);
  private final SafeTalonFX leftSlaveFalcon = new SafeTalonFX(Constants.LEFT_SLAVE_PORT);
  private final SafeTalonFX rightMasterFalcon = new SafeTalonFX(Constants.RIGHT_MASTER_PORT);
  private final SafeTalonFX rightSlaveFalcon = new SafeTalonFX(Constants.RIGHT_SLAVE_PORT);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMasterFalcon, rightMasterFalcon);
  public Orchestra orchestra;

  private NetworkTableEntry leftCurrent;
  private NetworkTableEntry leftPosition;
  private NetworkTableEntry leftVelocity;

  private NetworkTableEntry rightCurrent;
  private NetworkTableEntry rightPosition;
  private NetworkTableEntry rightVelocity;

  public DrivetrainFalcon() {
    leftSlaveFalcon.follow(leftMasterFalcon);
    rightSlaveFalcon.follow(rightMasterFalcon);

    leftMasterFalcon.setInverted(true);
    leftSlaveFalcon.setInverted(true);

    leftMasterFalcon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);
    rightMasterFalcon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);

    diffDrive.setRightSideInverted(false);

    configureSmartDashboard();

    orchestra = new Orchestra();

    orchestra.addInstrument(leftMasterFalcon);
    orchestra.addInstrument(rightMasterFalcon);
    orchestra.addInstrument(leftSlaveFalcon);
    orchestra.addInstrument(rightSlaveFalcon);

    orchestra.loadMusic("test.chrp");

    diffDrive.setSafetyEnabled(false);
  }

  public void configureSmartDashboard(){
    leftCurrent = Shuffleboard.getTab("Drivetrain")
      .add("Left Current", 0)
      .withSize(2,2)
      .withPosition(0, 0)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();
    leftPosition = Shuffleboard.getTab("Drivetrain")
      .add("Left Position", 0)
      .withSize(2,2)
      .withPosition(2, 0)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();
    leftVelocity = Shuffleboard.getTab("Drivetrain")
      .add("Left Velocity", 0)
      .withSize(2,2)
      .withPosition(4, 0)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();

    rightCurrent = Shuffleboard.getTab("Drivetrain")
      .add("Right Current", 0)
      .withSize(2,2)
      .withPosition(0, 2)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();
    rightPosition = Shuffleboard.getTab("Drivetrain")
      .add("Right Position", 0)
      .withSize(2,2)
      .withPosition(2, 2)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();
    rightVelocity = Shuffleboard.getTab("Drivetrain")
      .add("Right Velocity", 0)
      .withSize(2,2)
      .withPosition(4, 2)
      .withWidget(BuiltInWidgets.kGraph)
      .getEntry();
  }

  @Override
  public void periodic() {
    leftCurrent.setNumber(leftMasterFalcon.getStatorCurrent());
    leftPosition.setNumber(leftMasterFalcon.getSelectedSensorPosition());
    leftVelocity.setNumber(leftMasterFalcon.getSelectedSensorVelocity());

    rightCurrent.setNumber(rightMasterFalcon.getStatorCurrent());
    rightPosition.setNumber(rightMasterFalcon.getSelectedSensorPosition());
    rightVelocity.setNumber(rightMasterFalcon.getSelectedSensorVelocity());
  }

  public void arcadeDrive(double speed, double rotation){
    diffDrive.arcadeDrive(speed, rotation);
  }

  public void stop(){
    leftMasterFalcon.set(0);
    rightMasterFalcon.set(0);
  }

}
