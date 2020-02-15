/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.SafeTalonFX;
import frc.robot.util.NavX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainFalcon extends SubsystemBase {

  private final SafeTalonFX leftMasterFalcon = new SafeTalonFX(Constants.LEFT_MASTER_PORT);
  private final SafeTalonFX rightMasterFalcon = new SafeTalonFX(Constants.RIGHT_MASTER_PORT);

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
    if (Robot.isReal()) {
      SafeTalonFX leftSlaveFalcon = new SafeTalonFX(Constants.LEFT_SLAVE_PORT);
      SafeTalonFX rightSlaveFalcon = new SafeTalonFX(Constants.RIGHT_SLAVE_PORT);

      leftSlaveFalcon.follow(leftMasterFalcon);
      rightSlaveFalcon.follow(rightMasterFalcon);

      leftMasterFalcon.setInverted(true);

      leftMasterFalcon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);
      rightMasterFalcon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);

      orchestra = new Orchestra();
      orchestra.addInstrument(leftMasterFalcon);
      orchestra.addInstrument(rightMasterFalcon);
      orchestra.addInstrument(leftSlaveFalcon);
      orchestra.addInstrument(rightSlaveFalcon);

      orchestra.loadMusic("test.chrp");
    }

    diffDrive.setRightSideInverted(false);
    diffDrive.setSafetyEnabled(false);

    configurePID();
    configureMotionMagic();
    configureSmartDashboard();
  }

  private void configurePID() {
    leftMasterFalcon.config_kF(0, Constants.LEFT_MASTER_FF);
    leftMasterFalcon.config_kP(0, Constants.LEFT_MASTER_P);
    leftMasterFalcon.config_kI(0, Constants.LEFT_MASTER_I);
    leftMasterFalcon.config_kP(0, Constants.LEFT_MASTER_D);

    rightMasterFalcon.config_kF(0, Constants.RIGHT_MASTER_FF);
    rightMasterFalcon.config_kP(0, Constants.RIGHT_MASTER_P);
    rightMasterFalcon.config_kI(0, Constants.RIGHT_MASTER_I);
    rightMasterFalcon.config_kD(0, Constants.RIGHT_MASTER_D);
  }

  private void configureMotionMagic() {
    leftMasterFalcon.configMotionCruiseVelocity(Constants.LEFT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    leftMasterFalcon.configMotionAcceleration(Constants.LEFT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
    
    rightMasterFalcon.configMotionCruiseVelocity(Constants.RIGHT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    rightMasterFalcon.configMotionAcceleration(Constants.RIGHT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
  }

  private void configureSmartDashboard() {
    leftCurrent = generateEntry("Left Current", 0, 0);
    leftPosition = generateEntry("Left Position", 2, 0);
    leftVelocity = generateEntry("Left Velocity", 4, 0);
    rightCurrent = generateEntry("Right Current", 0, 2);
    rightPosition = generateEntry("Right Position", 2, 2);
    rightVelocity = generateEntry("Right Velocity", 4, 2);
  }

  @Override
  public void periodic() {
    if (Robot.isReal()) {
      leftCurrent.setNumber(leftMasterFalcon.getStatorCurrent());
      leftPosition.setNumber(leftMasterFalcon.getSelectedSensorPosition());
      leftVelocity.setNumber(leftMasterFalcon.getSelectedSensorVelocity());

      rightCurrent.setNumber(rightMasterFalcon.getStatorCurrent());
      rightPosition.setNumber(rightMasterFalcon.getSelectedSensorPosition());
      rightVelocity.setNumber(rightMasterFalcon.getSelectedSensorVelocity());
    } else {
      leftCurrent.setNumber(0.0);
      leftPosition.setNumber(0.0);
      leftVelocity.setNumber(0.0);

      rightCurrent.setNumber(0.0);
      rightPosition.setNumber(0.0);
      rightVelocity.setNumber(0.0);
    }
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
    rightMasterFalcon.stopMotor();
  }

  /**
   * Helper function to generate NetworkTableEntries
   */
  private NetworkTableEntry generateEntry(String entryName, int columnIndex, int rowIndex) {
    return Shuffleboard.getTab("Drivetrain")
            .add(entryName, 0)
            .withSize(2,2)
            .withPosition(columnIndex, rowIndex)
            .withWidget(BuiltInWidgets.kGraph)
            .getEntry();
  }
}
