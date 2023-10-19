package frc.led;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Comet extends CommandBase
{
  private final LEDRing ring;

  public Comet(LEDRing ring)
  {
    this.ring = ring;
    addRequirements(ring);
  }

  @Override
  public boolean runsWhenDisabled()
  {
    return true;
  }

  @Override
  public void execute()
  {
    ring.clear();

    // Step the 'active' LED every 50 ms
    int active = (int) ((System.currentTimeMillis()  / 50) % LEDRing.N);

    // Active LED: Purple
    ring.buffer.setRGB(active, 255, 100, 255);

    // Half of the LEDs before active one: dimming variant of blue
    for (int i=1; i<LEDRing.N/2; ++i)
    {
      // active-1, active-2, ...
      int prev = (active+LEDRing.N-i) % LEDRing.N;
      // Go down to blue=0 in N/2 steps
      ring.buffer.setRGB(prev, 0, 0, 255*(LEDRing.N/2-i)/(LEDRing.N/2));
    }

    ring.led.setData(ring.buffer);
  }  
}
