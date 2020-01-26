package frc.robot;

public final class Constants {
    public static int LEFT_MASTER_PORT = 1;
    public static int LEFT_SLAVE_PORT = 2;
    public static int RIGHT_MASTER_PORT = 3;
    public static int RIGHT_SLAVE_PORT = 4;

    public static int CLIMB_TALON = 6;

    public static double LEFT_MASTER_FF = 0.2;
    public static double LEFT_MASTER_P = 0.2;
    public static double LEFT_MASTER_I = 0;
    public static double LEFT_MASTER_D = 0;

    public static int LEFT_MASTER_ACCELERATION = 0;
    public static int LEFT_MASTER_VELOCITY = 0;

    public static double RIGHT_MASTER_FF = 0.2;
    public static double RIGHT_MASTER_P = 0.2;
    public static double RIGHT_MASTER_I = 0;
    public static double RIGHT_MASTER_D = 0;

    public static int RIGHT_MASTER_ACCELERATION = 3300;
    public static int RIGHT_MASTER_VELOCITY = 3300;

    public static int kTIMEOUT_MS = 0;

    public static int TOP_SHOOTER_PORT = 6;
    public static int BOTTOM_SHOOTER_PORT = 7;

    public static double SHOOTER_FF = 0;
    public static double SHOOTER_P = 5e-5;
    public static double SHOOTER_I = 1e-6;
    public static double SHOOTER_D = 0;
  
    // Neo motor contants
    public static double NEO_MAX_RPM = 5676; // http://www.revrobotics.com/rev-21-1650/ "Empirical Free Speed"
  
    public static int INTAKE_PORT = 5;
    public static double DRIVETRAIN_RAMP = 0.5;
    public static int INTAKE_Wheel_PORT = 8;
}
