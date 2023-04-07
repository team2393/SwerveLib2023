// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.swervelib;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** OI for serving */
public class SwerveOI
{
  public static final CommandXboxController joystick = new CommandXboxController(0);

  private static double filter(double value)
  {
    value = MathUtil.applyDeadband(value, 0.1);
    // Square
    return value * Math.abs(value);
  }

  public static double getForwardSpeed()
  {
    return SwerveDrivetrain.MAX_METERS_PER_SEC * filter(-joystick.getRightY());
  }

  public static double getLeftSpeed()
  {
    return SwerveDrivetrain.MAX_METERS_PER_SEC * filter(-joystick.getRightX());
  }

  public static double getRotationSpeed()
  {
    return SwerveDrivetrain.MAX_ROTATION_DEG_PER_SEC * filter(-joystick.getLeftX());
  }

  public static Trigger reset()
  {
    return joystick.back();
  }

  public static Trigger selectRelative()
  {
    return joystick.rightBumper();
  }

  public static Trigger selectAbsolute()
  {
    return joystick.leftBumper();
  }
}
