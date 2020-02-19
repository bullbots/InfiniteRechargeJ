/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeTest extends CommandBase {
  private NetworkTableEntry intake_Motor_Current;
  private NetworkTableEntry intake_Motor_Encoder;
  private NetworkTableEntry wheel_Motor_Current;
  private NetworkTableEntry wheel_Motor_Encoder;
  private NetworkTableEntry intake_Motor_Velocity;
  private NetworkTableEntry wheel_Motor_Velocity;

  /**
   * Creates a new IntakeTest.
   */
  private Intake intake;

  public IntakeTest(Intake intake) {
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake_Motor_Encoder = Shuffleboard.getTab("intake_Test")
    .add("intake Encoder", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();
    intake_Motor_Current = Shuffleboard.getTab("intake_Test")
    .add("intake Current", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();
    intake_Motor_Velocity = Shuffleboard.getTab("intake_Test")
    .add("intake Velocity", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();

    wheel_Motor_Velocity = Shuffleboard.getTab("intake_Test")
    .add("wheel Velocity", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();
    wheel_Motor_Encoder = Shuffleboard.getTab("intake_Test")
    .add("wheel Encoder", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();
    wheel_Motor_Current = Shuffleboard.getTab("intake_Test")
    .add("wheel Current", 0)
    .withSize(2, 2)
    .withPosition(0, 4)
    .withWidget(BuiltInWidgets.kGraph)
    .getEntry();
  }

  @Override
  public void execute() {
    
    
    double intakeMotorCurrent = intake.getIntakeOutputCurrent();
    double wheelMotorCurrent = intake.getWheelOutputCurrent();

    int intakeMotorEncoder = intake.getIntakeEncoderTicks();
    int wheelMotorEncoder = intake.getWheelEncoderTicks();

    int intakeMotorVelocity = intake.getIntakeVelocity();
    int wheelMotorVelocity = intake.getWheelVelocity();





    intake_Motor_Current.setDouble(intakeMotorCurrent);
    wheel_Motor_Current.setDouble(wheelMotorCurrent);

    intake_Motor_Encoder.setNumber(intakeMotorEncoder);
    wheel_Motor_Encoder.setNumber(wheelMotorEncoder);

    intake_Motor_Velocity.setNumber(intakeMotorVelocity);
    wheel_Motor_Velocity.setNumber(wheelMotorVelocity);



    double in = SmartDashboard.getNumber("intake_Test", 0);
    in = in > 1? 1: in;
    in = in < -1? -1: in;
    // intake.set(in);
  }

  @Override
  public void end(boolean interrupted) {
    // intake.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
