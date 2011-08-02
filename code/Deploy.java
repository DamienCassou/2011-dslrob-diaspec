// Deployment script that creates ROS nodes and DiaSpec
// component instances
public class Deploy extends MainDeploy
                    implements NodeMain {

  private Node node;

  // starting point defined by ROS
  @Override // from ROS NodeMain
  public void main(NodeConfiguration configuration) {
    // creates a ROS node
    node = new DefaultNode("laser_cmd", configuration);
    addLaserScan();
    addLight();
    // this is defined in the MainDeploy abstract class
    deployAll();
  }

  private void addLaserScan() {
    RosLaserScan scan = new RosLaserScan();
    // asks ROS to send laser scan messages to laserScan
    node.createSubscriber("/ATRV/Sick", 
			  "sensor_msgs/LaserScan", scan);
    // schedules for deployment
    add(laserScan);
  }

  private void addLight() {
    // allows the application to send messages to ROS
    Publisher<Bool> rosPublisher;
    rosPublisher = node.createPublisher("/ATRV/LightAct",
					"std_msgs/Bool");
    RosLight lightPublisher = new RosLight(rosPublisher);
    // schedules for deployment
    add(lightPublisher);
  }

  // automatically called by the programming framework
  @Override // from super class
  protected AbstractRandomMotion createRandomMotion() {
    // creates a new instance of a context operator that
    // will be deployed by deployAll()
    return new RandomMotion();
  }
}
