// Implementation of LaserScan from Listing#~\ref{listing:design}# line#~\ref{design:laserscan-b}#
public class RosLaserScan extends AbstractLaserScan
                   implements MessageListener<LaserScan> {

  private boolean isStarted = false;

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:on}# and line#~\ref{design:laserscan-onoff}#
  @Override // from super class
  protected void on() throws Exception {
    isStarted = true;
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:on}# and line#~\ref{design:laserscan-onoff}#
  @Override // from super class
  protected void off() throws Exception {
    isStarted = false;
  }

  // triggered when ROS publishes a LaserScan message
  @Override // from ROS MessageListener
  public void onNewMessage(LaserScan message) {
    float[] ranges = message.ranges;
    if (isStarted) {
      // sends the list of floats to subscribed
      // context operators through the source defined
      // in Listing#~\ref{listing:design}# line#~\ref{design:laserscan-ranges}#
      publishRanges(convert(ranges));
    }
  }

  private List<Float> convert(float[] ranges) {
    // converts a float[] to a List<Float>
  }
}
