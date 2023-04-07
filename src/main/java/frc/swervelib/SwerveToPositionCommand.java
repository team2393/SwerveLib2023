// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.swervelib;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Command for swerving to a position */
public class SwerveToPositionCommand extends CommandBase
{
  /** Proportional gain for distance control */
  private static final double P = 2.0;

  /** Max. speed */
  private static final double MAX_SPEED = 2.0;

  /** Max. acceleration */
  private static final double ACCEL = 1.0;

  private final SwerveDrivetrain drivetrain;
  private final double x, y;
  private double last_speed, distance;

  /** @param drivetrain
   *  @param x Desired X position
   *  @param y Desired Y position
   */
  public SwerveToPositionCommand(SwerveDrivetrain drivetrain, double x, double y)
  {
    this.drivetrain = drivetrain;
    this.x = x;
    this.y = y;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize()
  {
    last_speed = 0.0;
    distance = 20.0;
  }

  public void execute()
  {
    Pose2d pose = drivetrain.getPose();
    double dx = x - pose.getX();
    double dy = y - pose.getY();
    
    // Direct heading to the desired location
    double angle = Math.toDegrees(Math.atan2(dy, dx)) - drivetrain.getHeading().getDegrees();

    // Proportional control of speed based on distance
    distance = Math.hypot(dx, dy);
    double speed = Math.min(MAX_SPEED, P*distance);

    // Limit acceleration
    if (speed > last_speed)
      speed = Math.min(speed, last_speed + ACCEL * TimedRobot.kDefaultPeriod);
    last_speed = speed;

    drivetrain.drive(angle, speed);
  }

  @Override
  public boolean isFinished()
  {
    // Within 5 cm?
    return distance < 0.05;
  }
}
