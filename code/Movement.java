public class Motion extends AbstractMotion {

  @Override
  public Twist onObstacleDetection(Obstacle obstacle) {
    Twist cmd = new Twist();
    if (obstacle.getIsDetected()) {
      cmd.angular.z = angleVelocity(obstacle.getRanges());
    } else {
      cmd.linear.x = new Float(1);
    }
    return cmd;
  }

  private int middle(List<Float> ranges) {
    return ranges.size() / 2;
  }

  private Float angleVelocity(List<Float> ranges) {
    double midA = 0, midB = 0;
    // we look to the left and to the right and
    // decide which ones has more space
    for (int i = 0; i < middle(ranges); i++) {
      midA += ranges.get(i);
    }
    for (int i = middle(ranges); i < ranges.size(); i++) {
      midB += ranges.get(i);
    }
    if (midA > midB) {
      return new Float(-1.0);
    } else {
      return new Float(1.0);
    }
  }
}
