// Implementation of LaserScan from Listing#~\ref{listing:design}# line#~\ref{design:laserscan-b}#
public class RosLaserScan extends AbstractLaserScan
  implements MessageListener<LaserScan> {

  // triggered when ROS publishes a LaserScan message
  @Override // from ROS MessageListener
    public void onNewMessage(LaserScan message) {
    float[] ranges = message.ranges;
    // sends the list of floats to subscribed
    // context operators through the source defined
    // in Listing#~\ref{listing:design}# line#~\ref{design:laserscan-ranges}#
    publishRanges(convert(ranges));
  }

  private List<Float> convert(float[] ranges) {
    // converts a float[] to a List<Float>
  }
}
