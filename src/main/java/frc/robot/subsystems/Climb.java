package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private CANSparkMax climb_spark;

    private CANEncoder climb_encoder;

    private CANPIDController climb_pid_controller;

    List<Integer> velocityRange = IntStream.rangeClosed(-100, 100).boxed().collect(Collectors.toList());
    private int curClimbIndex = 0;

    private enum MotorPlacement {
      Climb
    }
    
    private NetworkTableEntry climbVelocity;
    public Climb() {
        climb_spark = new CANSparkMax(Constants.CLIMB_SPARK_PORT, MotorType.kBrushless);

        climb_spark.restoreFactoryDefaults();

        climb_spark.clearFaults();

        climb_encoder = climb_spark.getEncoder();

        climb_pid_controller = climb_spark.getPIDController();

        configurePID();
        configureShuffleBoard();
    }

  private void configurePID() {
    climb_pid_controller.setFF(Constants.CLIMB_SPARK_FF);
    climb_pid_controller.setP(Constants.CLIMB_SPARK_P);
    climb_pid_controller.setI(Constants.CLIMB_SPARK_I);
    climb_pid_controller.setD(Constants.CLIMB_SPARK_D);
    }

	private void configureShuffleBoard() {
    climbVelocity = Shuffleboard.getTab("Diagnostics")
        .add("Climb Encoder Velocity", 0)
        .withSize(2, 2)
        .withPosition(0, 4)
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
  }
  
  public double getVelocity(MotorPlacement motorPlacement) {
    double curVal = 0;
    if (Robot.isReal()) {
        switch (motorPlacement) {
            case Climb:
                curVal = climb_encoder.getVelocity();
                break;
        }
    } else {
        int curIndex = 0;

        switch (motorPlacement) {
            case Climb:
                curIndex = curClimbIndex;
                break;
        }

        curVal = velocityRange.get(curIndex);

        if (curIndex >= velocityRange.size() - 1) {
            switch (motorPlacement) {
                case Climb:
                    curClimbIndex = 0;
                    break;
            }
        } else {
            switch (motorPlacement) {
                case Climb:
                    curClimbIndex++;
                    break;
              }
            }
          }
    return curVal;
        }
      
    /** This tells the code to set the climb talon's contol mode and magnitude
     * @param control_mode This is the controm mode of the motor
     * @param magnitude This the magnitude of the motor
     */
    public void set(ControlMode control_mode, double magnitude) {
        climb_spark.set(magnitude);
    }

    /** This tells the motor what set it's speed to
     * @param speed This is the speed of the motor
     */
    public void set(double speed) {
        climb_spark.set(speed);
    }


  public double[] getvelocities(){
    double climb_vel = climb_encoder.getVelocity();

    return new double[] {climb_vel};
  }
  
  public void setReference(double climb_vel){
    climb_pid_controller.setReference(climb_vel, ControlType.kVelocity);
  }

  public void periodic(){
    climbVelocity.setDouble(getVelocity(MotorPlacement.Climb));
  }

public void set(int i, int j) {
}

public void set(ControlMode percentoutput, int i, int j) {
}
} 

/*
                    _
                  _| |
                 |  _|
                 | | |   ______
                _| |_|__|     _|__
              _|             |_|  |
            _|                    |
           |                      |
           |__                   _|
             |             ______|
             |__           |
             __|           |___
        ||__|__|               |
        |                   ___|
        |__               _|
          |_           __|
            |___      _|
                |    |_
                |______|


    This is Yoshi
Made by Triston
*/