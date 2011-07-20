// Deployment script that creates ROS nodes and DiaSpec
// component instances
public class Deploy extends MainDeploy
                    implements NodeMain {

  private Node node;

  // starting point defined by ROS
  @Override
  public void main(NodeConfiguration configuration) {
    // creation of a ROS node
    node = new DefaultNode("laser_cmd", configuration);
    addLaserScan();
    addLight();
    // this is defined in the MainDeploy abstract class
    deployAll();
  }

  private void addLaserScan() {
    RosLaserScan laserScan = new RosLaserScan();
    // asks ROS to send laser scan messages to laserScan
    node.createSubscriber("/ATRV/Sick", 
			  "sensor_msgs/LaserScan",
			  laserScan);
    // schedules for deployment
    add(laserScan);
  }

  private void addLight() {
    // allows the application to send messages to the ROS
    Publisher<Bool> rosPublisher;
    rosPublisher = node.createPublisher("/ATRV/LightAct",
					"std_msgs/Bool");
    RosLight lightPublisher = new RosLight(rosPublisher);
    // schedules for deployment
    add(lightPublisher);
  }

  // required by the programming framework
  @Override
  protected AbstractMovement getMovementInstance() {
    // creates a new instance of a context operator that
    // will be deployed by deployAll()
    return new Movement();
  }
  [...]
}
