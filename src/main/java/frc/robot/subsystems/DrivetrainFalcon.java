/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.util.SafeTalonFX;
import frc.robot.util.NavX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
  private final NavX gyro = new NavX();
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
    diffDrive.setSafetyEnabled(false);

    orchestra = new Orchestra();
    orchestra.addInstrument(leftMasterFalcon);
    orchestra.addInstrument(rightMasterFalcon);
    orchestra.addInstrument(leftSlaveFalcon);
    orchestra.addInstrument(rightSlaveFalcon);

    orchestra.loadMusic("test.chrp");

    configurePID();
    configureMotionMagic();
    configureSmartDashboard();
  }

  private void configurePID(){
    leftMasterFalcon.config_kF(0, Constants.LEFT_MASTER_FF);
    leftMasterFalcon.config_kP(0, Constants.LEFT_MASTER_P);
    leftMasterFalcon.config_kI(0, Constants.LEFT_MASTER_I);
    leftMasterFalcon.config_kP(0, Constants.LEFT_MASTER_D);

    rightMasterFalcon.config_kF(0, Constants.RIGHT_MASTER_FF);
    rightMasterFalcon.config_kP(0, Constants.RIGHT_MASTER_P);
    rightMasterFalcon.config_kI(0, Constants.RIGHT_MASTER_I);
    rightMasterFalcon.config_kD(0, Constants.RIGHT_MASTER_D);
  }

  private void configureMotionMagic(){
    leftMasterFalcon.configMotionCruiseVelocity(Constants.LEFT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    leftMasterFalcon.configMotionAcceleration(Constants.LEFT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
    
    rightMasterFalcon.configMotionCruiseVelocity(Constants.RIGHT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    rightMasterFalcon.configMotionAcceleration(Constants.RIGHT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
  }

  private void configureSmartDashboard(){
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

  /**
   * Moves the robot according to joystick input
   * @param speed double
   * @param rotation double
   */
  public void arcadeDrive(double speed, double rotation){
    diffDrive.arcadeDrive(speed, rotation);
  }

  /**
   * Sets the encoder values back to zero
   */
  public void resetEncoders(){
    leftMasterFalcon.setSelectedSensorPosition(0);
    rightMasterFalcon.setSelectedSensorPosition(0);
  }

  /**
   * 
   * @return double array of positions [left, right]
   */
  public double[] getPositions(){
    return new double[] {leftMasterFalcon.getSelectedSensorPosition(), rightMasterFalcon.getSelectedSensorPosition()};
  }

  /**
   * 
   * @return double array of velocities [left, right]
   */
  public double[] getVelocities() {
    return new double[] {leftMasterFalcon.getSelectedSensorVelocity(), rightMasterFalcon.getSelectedSensorVelocity()};
  }

  /**
   * Sets the left and right motors to a percent output
   * @param leftPercent double
   * @param rightPercent double
   */
  public void set(double leftPercent, double rightPercent){
    leftMasterFalcon.set(leftPercent);
    rightMasterFalcon.set(rightPercent);
  }

  /**
   * Sets the left and right motors to a specified control mode
   * @param controlMode ControlMode
   * @param leftMagnitude double
   * @param rightMagnitude double
   */
  public void set(ControlMode controlMode, double leftMagnitude, double rightMagnitude) {
    leftMasterFalcon.set(controlMode, leftMagnitude);
    rightMasterFalcon.set(controlMode, rightMagnitude);
  }

  /**
   * Immediately stops the drivetrain, only use in emergencies
   */
  public void stop(){
    leftMasterFalcon.stopMotor();
    leftSlaveFalcon.stopMotor();
    rightMasterFalcon.stopMotor();
    rightSlaveFalcon.stopMotor();
  }

}
