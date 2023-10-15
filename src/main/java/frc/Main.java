// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main
{
  public static void main(String... args)
  {
    // RobotBase.startRobot(SwerveRob::new);
    // RobotBase.startRobot(() -> new frc.swervelib.RotatatorDemoRobot(new frc.swervebot.Rotator;Rotator(0, 0.0)));
    // RobotBase.startRobot(() -> new frc.swervelib.DriverDemoRobot(new frc.swervebot.Driver(0)));
    // RobotBase.startRobot(frc.swervebot.SwerveModuleDemoRobot::new);

    RobotBase.startRobot(frc.swervebot.SwerveBotRobot::new);
  }
}
