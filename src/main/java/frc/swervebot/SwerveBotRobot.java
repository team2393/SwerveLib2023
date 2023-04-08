// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.swervebot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.AutoNoMouse;
import frc.CommandRobotBase;
import frc.swervelib.AbsoluteSwerveCommand;
import frc.swervelib.RelativeSwerveCommand;
import frc.swervelib.ResetPositionCommand;
import frc.swervelib.SwerveOI;

/** ServeBot */
public class SwerveBotRobot extends CommandRobotBase
{
  private final SwervebotDrivetrain drivetrain = new SwervebotDrivetrain();

  private final CommandBase drive_relative = new RelativeSwerveCommand(drivetrain);
  private final CommandBase drive_absolute = new AbsoluteSwerveCommand(drivetrain);

  private final SendableChooser<Command> autos = new SendableChooser<>();

  @Override
  public void robotInit()
  {
    super.robotInit();

    autos.setDefaultOption("Nothing", new PrintCommand("Doing nothing"));
    for (Command auto : AutoNoMouse.createAutoCommands(drivetrain))
      autos.addOption(auto.getName(), auto);
    SmartDashboard.putData("Auto Options", autos);
  }
  
  @Override
  public void disabledPeriodic()
  {
    AutoNoMouse.indicateStart(drivetrain, autos.getSelected());
  }

  @Override
  public void teleopInit()
  {
    CommandScheduler.getInstance().getActiveButtonLoop().clear();
    SwerveOI.reset().onTrue(new ResetPositionCommand(drivetrain));
    SwerveOI.selectAbsolute().onTrue(drive_absolute);
    SwerveOI.selectRelative().onTrue(drive_relative);

    drive_relative.schedule();
  }

  @Override
  public void teleopPeriodic()
  {
  }

  @Override
  public void autonomousInit()
  {
    autos.getSelected().schedule();
  }

  @Override
  public void autonomousPeriodic()
  {
    // Nothing to do but let command run
  }
}
