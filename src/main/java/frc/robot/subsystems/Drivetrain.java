

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

    left_master_talon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);
    right_master_talon.configClosedloopRamp(Constants.DRIVETRAIN_RAMP);

    left_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    right_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    left_slave_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    right_slave_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    drive = new MecanumDrive(left_master_talon, left_slave_talon, right_master_talon, right_slave_talon);
    configurePID();
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

  public void diffDrive(double x, double y, double z){
    x = DeadBand(x);
    y = DeadBand(y);
    z = DeadBand(z);
    drive.driveCartesian(y, x, z);
  }

  public double DeadBand(double num) {
      double deadband_value = .1;
      if (Math.abs(num) < deadband_value) {
        num = 0;
      }
      return num;
    }


  public void stop(){
    left_master_talon.stopMotor();
    right_master_talon.stopMotor();
  }

  public void periodic() {
    rightMasterVelocity.setDouble(right_master_talon.getSelectedSensorVelocity());
    leftMasterVelocity.setDouble(left_master_talon.getSelectedSensorVelocity());
  }

  /* Sets Drivetrain control mode and magnitudes for left and right side

    Args:
        control_mode (WPI_TalonSRX.ControlMode): Control Mode for both motor controllers
        left_magnitude (float): Magnitude for the left side
        right_magnitude (float): Magnitude for the right side
  */
  public void set(ControlMode control_mode, float left_magnitude, float right_magnitude) {
    left_master_talon.set(control_mode, left_magnitude);
    right_master_talon.set(control_mode, right_magnitude);
  }

  /* Sets all motor outputs to zero, run when robot is disabled
   */
  public void stop() {
    left_master_talon.set(0);
    right_master_talon.set(0);
  }
}
