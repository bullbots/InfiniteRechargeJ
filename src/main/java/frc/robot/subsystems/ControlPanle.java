/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.SafeTalonFX;

public class ControlPanle extends SubsystemBase {
  private final SafeTalonFX controlFalcon = new SafeTalonFX(Constants.CONTROL_PORT);
  
  private NetworkTableEntry controlCurrent;
  private NetworkTableEntry controlPosition;
  private NetworkTableEntry controlVelocity;

  public ControlPanle() {
    if (Robot.isReal()){
      SafeTalonFX controlFalcon = new SafeTalonFX(Constants.CONTROL_PORT);
    }

  }

  private void configureSmartDashboard() {
    controlCurrent = generateEntry("Control Current", 0, 0);
    controlPosition = generateEntry("Control Position", 2, 0);
    controlVelocity = generateEntry("Control Velocity", 4, 0);
  }

  @Override
  public void periodic() {
    if (Robot.isReal()) {

      controlCurrent.setNumber(controlFalcon.getStatorCurrent());
      controlPosition.setNumber(controlFalcon.getSelectedSensorPosition());
      controlVelocity.setNumber(controlFalcon.getSelectedSensorVelocity());
    }
  }

  public double[] getPositions(){
    return new double[] {controlFalcon.getSelectedSensorPosition()};

  }
  private NetworkTableEntry generateEntry(String entryName, int columnIndex, int rowIndex) {
    return Shuffleboard.getTab("Control")
            .add(entryName,0)
            .withSize(2, 2)
            .withPosition(columnIndex, rowIndex)
            .withWidget(BuiltInWidgets.kGraph)
            .getEntry();
  }
}
