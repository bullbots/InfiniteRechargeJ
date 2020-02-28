/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import frc.robot.util.SafeSparkMax;

public class ControlPanel extends SubsystemBase {

  private SafeSparkMax control_motor;

  private CANEncoder control_encoder;

  private CANPIDController control_pid_controller;

  private NetworkTableEntry controlVelocity;

  List<Integer> velocityRange = IntStream.rangeClosed(-100, 100).boxed().collect(Collectors.toList());

  
  /**
   * Creates a new ControlPanel.
   */
  public ControlPanel() {
    control_motor = new SafeSparkMax(Constants.CONTROL_PANEL_PORT, MotorType.kBrushless);


    control_encoder = control_motor.getEncoder();
    
    control_pid_controller = control_motor.getPIDController();
    configurePID();

  }

  private void configurePID() {
    control_pid_controller.setFF(Constants.CONTROL_FF);
    control_pid_controller.setP(Constants.CONTROL_P);
    control_pid_controller.setI(Constants.CONTROL_I);
    control_pid_controller.setD(Constants.CONTROL_D);

  }


  public void stop() {
    control_motor.stopMotor();
  }


  /**This gets the velocity if the control motor
 * @return curVal This returns the current velocity of the control panle motor 
 */
  public double getVelocity(){
  double curVal = 0;
  int curIndex =0;
   curVal = velocityRange.get(curIndex);

   return curVal;

  }

  public void set(double control_vel){
    ControlType controltype;
    controltype = ControlType.kVelocity; // We can change this as needed
    control_pid_controller.setReference(control_vel, controltype);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    controlVelocity.setDouble(getVelocity());
  }
}


/*


      ______________________________          
      \                            /
       \                          /
        \       |       |        /
         \      |       |       /
          \                    /
           \                  /
            \   |_      _|   /
             \   |______|   /
              \            /
               \          /
                \        /
                 \      /
                  \    /
                   \  /
                    \/


  This is a icicle it goes no where because it is only a coment. 
  This means you can look at him as long as you want.
  Plus he can't fall on your head and hurt you.
Made by Triston Van Wyk
*/
