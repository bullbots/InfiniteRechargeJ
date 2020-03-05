/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // User Input
  private static Joystick stick = new Joystick(0);
  
  private static JoystickButton trigger = new JoystickButton(stick, 1);

  private static JoystickButton button3 = new JoystickButton(stick, 3);

  private static Joystick button_board = new Joystick(1);

  private static JoystickButton climber_up = new JoystickButton(button_board, 1);
  private static JoystickButton climber_down = new JoystickButton(button_board, 2);
  private static JoystickButton control_panel = new JoystickButton(button_board, 3);
  private static JoystickButton control_panel_cw = new JoystickButton(button_board, 4);
  private static JoystickButton control_panel_ccw = new JoystickButton(button_board, 5);
  private static JoystickButton shooter_toggle = new JoystickButton(button_board, 6);
  
  // Subsystems
  private final Shooter shooter = new Shooter();
  private final DrivetrainFalcon drivetrain = new DrivetrainFalcon();
  private final Intake intake = new Intake();
  private final Climb climb = new Climb();

  private final Compressor compressor = new Compressor();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    compressor.start();
    
    drivetrain.setDefaultCommand(new JoystickDrive(
      drivetrain,
      () -> -stick.getY(),  // Because Negative Y is forward on the joysticks
      () -> stick.getX()
    ));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    trigger.whileHeld(new Shoot(shooter, () -> shooter_toggle.get()));

    climber_up.whileHeld(new ClimbUp(climb));
    climber_down.whileHeld(new ClimbDown(climb));
    // control_panel.toggleWhenPressed();  // Is this supposed to be a whileHeld?
    // control_panel_cw.whileHeld();
    // control_panel_ccw.whileHeld();

    button3.whileHeld(new IntakeBalls(intake));

    shooter_toggle.whenPressed(new RaiseShooter(shooter));
    shooter_toggle.whenReleased(new LowerShooter(shooter));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // return new PIDTune(drivetrain);
    return new MoveTimed(drivetrain, 3);
  }

  public void stopAllSubsystems(){
    drivetrain.stop();
  }
}


/*
This is a 16 wheeler 
|_|______|_|
| | |  | | |
    |  |
    |  |
    |  |
    |  |
    |  |
|_|_|__|_|_|
| |  ||  | |
     ||
     ||
     ||
     || 
|_|__||__|_|
| | |  | | |
    |  |
    |  |
    |  |
    |  |
    |  |
|_|_|__|_|_|
| |      | |
This is #3 in the car series.
Made by Triston Van Wyk
*/ 
