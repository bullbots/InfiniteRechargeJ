package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

public class Shooter extends SubsystemBase{

    private CANSparkMax top_shooter;
    private CANSparkMax bottom_shooter;

    private CANEncoder top_encoder;
    private CANEncoder bottom_encoder;

    private CANPIDController top_pid_controller;
    private CANPIDController bottom_pid_controller;

    public Shooter(){
        top_shooter = new CANSparkMax(Constants.TOP_SHOOTER_PORT, MotorType.kBrushless);
        bottom_shooter = new CANSparkMax(Constants.BOTTOM_SHOOTER_PORT, MotorType.kBrushless);

        top_shooter.clearFaults();
        bottom_shooter.clearFaults();

        top_encoder = top_shooter.getEncoder();
        bottom_encoder = bottom_shooter.getEncoder();

        top_pid_controller = top_shooter.getPIDController();
        bottom_pid_controller = bottom_shooter.getPIDController();

        configurePID();
    }

    private void configurePID(){
        top_pid_controller.setFF(Constants.SHOOTER_FF);
        top_pid_controller.setP(Constants.SHOOTER_P);
        top_pid_controller.setI(Constants.SHOOTER_I);
        top_pid_controller.setD(Constants.SHOOTER_D);

        bottom_pid_controller.setFF(Constants.SHOOTER_FF);
        bottom_pid_controller.setP(Constants.SHOOTER_P);
        bottom_pid_controller.setI(Constants.SHOOTER_I);
        bottom_pid_controller.setD(Constants.SHOOTER_D);
    }

    public double[] getVelocities(){
        double top_vel = top_encoder.getVelocity();
        double bottom_vel = bottom_encoder.getVelocity();

        return new double[] {top_vel, bottom_vel};
    }

    public void set(double top_vel, double bottom_vel){
        top_pid_controller.setReference(top_vel, ControlType.kVelocity);
        bottom_pid_controller.setReference(bottom_vel, ControlType.kVelocity);
    }

    public void periodic(){
        // double kF = top_pid_controller.getFF();
        // double kP = top_pid_controller.getP();
        // double kI = top_pid_controller.getI();
        // double kD = top_pid_controller.getD();

        // double newF = SmartDashboard.getNumber("Shooter F", kF);
        // double newP = SmartDashboard.getNumber("Shooter P", kP);
        // double newI = SmartDashboard.getNumber("Shooter I", kI);
        // double newD = SmartDashboard.getNumber("Shooter D", kD);

        // top_pid_controller.setFF(newF);
        // top_pid_controller.setP(newP);
        // top_pid_controller.setI(newI);
        // top_pid_controller.setD(newD);

        SmartDashboard.putNumber("Top Encoder Velocity", top_encoder.getVelocity());
        SmartDashboard.putNumber("Bottom Encoder Velocity", bottom_encoder.getVelocity());

        SmartDashboard.putNumber("Top Current", top_shooter.getOutputCurrent());
        SmartDashboard.putNumber("Bottom Current", bottom_shooter.getOutputCurrent());

        SmartDashboard.putNumber("Top Temp", top_shooter.getMotorTemperature());
        SmartDashboard.putNumber("Bottom Temp", top_shooter.getMotorTemperature());
    }

}