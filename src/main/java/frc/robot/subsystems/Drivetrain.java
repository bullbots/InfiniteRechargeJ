

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private WPI_TalonSRX left_master_talon;
  private WPI_TalonSRX left_slave_talon;
  private WPI_TalonSRX right_master_talon;
  private WPI_TalonSRX right_slave_talon;
  private MecanumDrive drive;

  private double left_master_position;
  private double right_master_position;

  private NetworkTableEntry leftMasterVelocity;
  private NetworkTableEntry rightMasterVelocity;
  
  public Drivetrain() {
    left_master_talon = new WPI_TalonSRX(Constants.LEFT_MASTER_PORT);
    left_slave_talon = new WPI_TalonSRX(Constants.LEFT_SLAVE_PORT);
    right_master_talon = new WPI_TalonSRX(Constants.RIGHT_MASTER_PORT);
    right_slave_talon = new WPI_TalonSRX(Constants.RIGHT_SLAVE_PORT);

    left_master_talon.configFactoryDefault();
    left_slave_talon.configFactoryDefault();
    right_master_talon.configFactoryDefault();
    right_slave_talon.configFactoryDefault();

    // left_slave_talon.follow(left_master_talon);
    // right_slave_talon.follow(right_master_talon);

    // left_master_talon.setInverted(true);
    // left_slave_talon.setInverted(true);

    left_master_talon.configOpenloopRamp(Constants.DRIVETRAIN_RAMP);
    right_master_talon.configOpenloopRamp(Constants.DRIVETRAIN_RAMP);

    left_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    right_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    left_slave_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    right_slave_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    drive = new MecanumDrive(left_master_talon, left_slave_talon, right_master_talon, right_slave_talon);
    configurePID();
    configureMotionMagic();
    configureShuffleBoard();
  }

  private void configurePID(){
    left_master_talon.config_kF(0, Constants.LEFT_MASTER_FF);
    left_master_talon.config_kP(0, Constants.LEFT_MASTER_P);
    left_master_talon.config_kI(0, Constants.LEFT_MASTER_I);
    left_master_talon.config_kD(0, Constants.LEFT_MASTER_D);

    right_master_talon.config_kF(0, Constants.RIGHT_MASTER_FF);
    right_master_talon.config_kP(0, Constants.RIGHT_MASTER_P);
    right_master_talon.config_kI(0, Constants.RIGHT_MASTER_I);
    right_master_talon.config_kD(0, Constants.RIGHT_MASTER_D);
  }

  private void configureMotionMagic() {
    left_master_talon.configMotionCruiseVelocity(Constants.LEFT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    left_master_talon.configMotionAcceleration(Constants.LEFT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
    
    right_master_talon.configMotionCruiseVelocity(Constants.RIGHT_MASTER_VELOCITY, Constants.kTIMEOUT_MS);
    right_master_talon.configMotionAcceleration(Constants.RIGHT_MASTER_ACCELERATION, Constants.kTIMEOUT_MS);
  }

  private void configureShuffleBoard() {
    leftMasterVelocity = Shuffleboard.getTab("Diagnostics")
        .add("Left Encoder Velocity", 0)
        .withSize(2, 2)
        .withPosition(0, 0)
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
    rightMasterVelocity = Shuffleboard.getTab("Diagnostics")
        .add("Right Encoder Velocity", 0)
        .withSize(2, 2)
        .withPosition(2, 0)
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();

  }

/** This sets up the differental drive
 * @param x This is the x value of the robot
 * @param y This is the y value of the robot
 * @param z This is the z value of the robot
 */
  public void diffDrive(double y, double x, double z){
    double yStick = DeadBand(y);
    double xStick = DeadBand(x);
    double zStick = DeadBand(z);
    // Caution!!! xStick gets mapped to ySpeed and vice versa. Forward direction is X.
    drive.driveCartesian(
            xStick,  // ySpeed: The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
            yStick,  // xSpeed: The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
            zStick  // zRotation: The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
    );
  }

  public double DeadBand(double num) {
      double deadband_value = .1;
      if (Math.abs(num) < deadband_value) {
        num = 0;
      }
      return num;
    }

  public double[] getPosition() {
    left_master_position = left_master_talon.getSelectedSensorPosition();
    right_master_position = right_master_talon.getSelectedSensorPosition();
    return new double[] {left_master_position, right_master_position};
  } 

  public double[] setPositionZero() {
    left_master_talon.setSelectedSensorPosition(0);
    right_master_talon.setSelectedSensorPosition(0);
    return new double[] {left_master_position, right_master_position};
  }

  public void stop(){
    left_master_talon.stopMotor();
    right_master_talon.stopMotor();
  }

  public void periodic() {
    rightMasterVelocity.setDouble(right_master_talon.getSelectedSensorVelocity());
    leftMasterVelocity.setDouble(left_master_talon.getSelectedSensorVelocity());
  }


  /** Sets Drivetrain control mode and magnitudes for left and right side
   * @param control_mode This is the control mode
   * @param left_magnitude This is the left side magnitude
   * @param right_magnitude This is the rigth side magnitude
   */
  public void set(ControlMode control_mode, float left_magnitude, float right_magnitude) {
    left_master_talon.set(control_mode, left_magnitude);
    right_master_talon.set(control_mode, right_magnitude);
  }

  /* Sets all motor outputs to zero, run when robot is disabled
   */
}
