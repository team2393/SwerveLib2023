// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.swervelib;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.CommandRobotBase;

/** Robot for testing driver motors */
public class DriverDemoRobot extends CommandRobotBase
{
  private final DriverBase driver;
  private final XboxController joystick = new XboxController(0);

  public DriverDemoRobot(DriverBase driver)
  {
    this.driver = driver;
    SmartDashboard.setDefaultNumber("Period", 5.0);
    SmartDashboard.setDefaultNumber("Setpoint1", 0.5);
    SmartDashboard.setDefaultNumber("Setpoint2", 1.5);
    SmartDashboard.setDefaultNumber("Voltage", 0.0);
  }

  @Override
  public void teleopPeriodic()
  {
    if (joystick.getAButton())
    {
      double setpoint = -3.0 * joystick.getLeftY();
      driver.setSpeed(setpoint);
      SmartDashboard.putNumber("Setpoint", setpoint);
    }
    else if (true)
    {
      double voltage = SmartDashboard.getNumber("Voltage", 0);
      driver.setVoltage(voltage);
    }
    else
    {
      double voltage = -11.0 * joystick.getLeftY();
      driver.setVoltage(voltage);
      SmartDashboard.putNumber("Voltage", voltage);
    }
  }

  @Override
  public void autonomousPeriodic()
  {
    double setpoint = ((System.currentTimeMillis() / (int)(SmartDashboard.getNumber("Period", 5.0)*1000)) % 2 == 1)
                    ? SmartDashboard.getNumber("Setpoint1", 0.0)
                    : SmartDashboard.getNumber("Setpoint2", 90.0);
    driver.setSpeed(setpoint);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }
}
