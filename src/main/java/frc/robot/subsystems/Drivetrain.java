

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private WPI_TalonSRX left_master_talon;
  private WPI_TalonSRX left_slave_talon;
  private WPI_TalonSRX right_master_talon;
  private WPI_TalonSRX right_slave_talon;
  private MecanumDrive drive;
  
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

    left_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    right_master_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    drive = new MecanumDrive(left_master_talon, left_slave_talon, right_master_talon, right_slave_talon);

    configurePID();
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

  public void diffDrive(double x, double y, double z){
    drive.driveCartesian(y, x, z);
  }

  public void stop(){
    left_master_talon.stopMotor();
    left_slave_talon.stopMotor();
    right_master_talon.stopMotor();
    right_slave_talon.stopMotor();
  }
}
