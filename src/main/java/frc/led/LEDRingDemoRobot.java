package frc.led;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.CommandRobotBase;

public class LEDRingDemoRobot extends CommandRobotBase
{
  private final LEDRing ring = new LEDRing();

  @Override
  public void robotInit()
  {
    super.robotInit();
    ring.setDefaultCommand(new Comet(ring));
  }
}
