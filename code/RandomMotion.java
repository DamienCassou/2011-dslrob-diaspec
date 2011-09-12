// Implementation of RandomMotion from Listing#~\ref{listing:design}# line#~\ref{design:randommotion-b}#
public class RandomMotion extends AbstractRandomMotion {

  // automatically called by the programming framework
  // when ObstacleDetection sends a new value. The method
  // parameter is the value sent by ObstacleDetection
  // whose structure is defined in Listing#~\ref{listing:design}# line#~\ref{design:obstacle-b}#
  @Override // from super class
  public Twist onObstacleDetection(Obstacle obstacle) {
    Twist cmd = new Twist();
    if (obstacle.getIsDetected())
      // turn
      cmd.angular.z = angleVelocity(obstacle.getRanges());
    else
      // go straight
      cmd.linear.x = new Float(1);

    // value transmitted automatically by the programming
    // framework to subscribed operators (here 'Motion')
    return cmd;
  }

  private Float angleVelocity(List<Float> ranges) {
    double left = 0, right = 0;
    // we look to the left and to the right and decide
    // which side has more space
    for (int i = 0; i < middle(ranges); i++)
      left += ranges.get(i);
    for (int i = middle(ranges); i < ranges.size(); i++)
      right += ranges.get(i);
    if (left > right)
      return new Float(-1.0);
    else
      return new Float(1.0);
  }

  private int middle(List<Float> ranges) {
    return ranges.size() / 2;
  }
}
