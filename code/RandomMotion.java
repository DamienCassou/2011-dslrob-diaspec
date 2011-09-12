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
      cmd.angular.z = angleVelocity(obstacle.getRanges());
    else
      cmd.linear.x = new Float(1);

    // value transmitted automatically by the programming
    // framework to subscribed operators (here 'Motion')
    return cmd;
  }

  private int middle(List<Float> ranges) {
    return ranges.size() / 2;
  }

  private Float angleVelocity(List<Float> ranges) {
    double midA = 0, midB = 0;
    // we look to the left and to the right and decide
    // which one has more space
    for (int i = 0; i < middle(ranges); i++)
      midA += ranges.get(i);
    for (int i = middle(ranges); i < ranges.size(); i++)
      midB += ranges.get(i);
    if (midA > midB)
      return new Float(-1.0);
    else
      return new Float(1.0);
  }
}
