package frc.robot;

public final class Constants {

    /**
     * ALL PORTS GO HERE
     */

     // Drivetrain Ports
    public static int LEFT_MASTER_PORT = 1;
    public static int LEFT_SLAVE_PORT = 2;
    public static int RIGHT_MASTER_PORT = 3;
    public static int RIGHT_SLAVE_PORT = 4;

    // Intake Ports
    public static int INTAKE_PORT = 5;
    public static int INTAKE_Wheel_PORT = 8;

    // Shooter Ports
    public static int TOP_SHOOTER_PORT = 6;
    public static int BOTTOM_SHOOTER_PORT = 7;

    // Climb Ports
    public static int CLIMB_TALON = 6;


    /**
     * ALL DRIVETRAIN RELATED CONSTANTS GO HERE
     */

    public static double LEFT_VELOCITY_FF = 0.0463;
    public static double LEFT_VELOCITY_P = 0.15;
    public static double LEFT_VELOCITY_I = 0;
    public static double LEFT_VELOCITY_D = 0;

    public static int LEFT_MASTER_ACCELERATION = 0;
    public static int LEFT_MASTER_VELOCITY = 0;

    public static double RIGHT_VELOCITY_FF = 0.0473;
    public static double RIGHT_VELOCITY_P = 0.15;
    public static double RIGHT_VELOCITY_I = 0;
    public static double RIGHT_VELOCITY_D = 0;

    public static int RIGHT_MASTER_ACCELERATION = 3300;
    public static int RIGHT_MASTER_VELOCITY = 3300;

    public static double DRIVETRAIN_RAMP = 0.5;


    /**
     * ALL INTAKE RELATED CONSTANTS GO HERE
     */


    /**
     * ALL SHOOTER RELATED CONSTANTS GO HERE
     */

    public static double SHOOTER_FF = 0;
    public static double SHOOTER_P = 5e-5;
    public static double SHOOTER_I = 1e-6;
    public static double SHOOTER_D = 0;

    public static double NEO_MAX_RPM = 5676; // http://www.revrobotics.com/rev-21-1650/ "Empirical Free Speed"    


    /**
     * ALL CLIMB RELATED CONSTANTS GO HERE
     */


     /**
      * MISCELLANEOUS CONSTANTS
      */

    public static int kTIMEOUT_MS = 0;
}
