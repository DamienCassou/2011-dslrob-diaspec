// Implementation of LaserScan from Listing#~\ref{listing:design}# line#~\ref{design:laserscan-b}#
public class RosLaserScan extends AbstractLaserScan
                   implements MessageListener<LaserScan> {

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:on}#
  @Override
  protected void on() throws Exception {
    updateIsStarted(true);
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:off}#
  @Override
  protected void off() throws Exception {
    updateIsStarted(false);
  }

  // triggered when ROS publishes a LaserScan message
  @Override
  public void onNewMessage(LaserScan message) {
    float[] ranges = message.ranges;
    if (getIsStarted()) {
      // sends the list of floats to subscribed
      // context operators through the source defined
      // in Listing#~\ref{listing:design}# line#~\ref{design:laserscan-ranges}#
      publishRanges(convert(ranges));
    }
  }

  private List<Float> convert(float[] ranges) {
    List<Float> newRanges;
    newRanges = new ArrayList<Float>(ranges.length);
    for (float range : ranges) {
      newRanges.add(range);
    }
    return newRanges;
  }
}
