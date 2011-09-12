// Deployment script that creates ROS nodes and DiaSpec
// component instances
public class Deploy extends MainDeploy
                    implements NodeMain {

  private Node node;

  // starting point defined by ROS
  @Override // from ROS NodeMain
  public void main(NodeConfiguration configuration) {
    // creates a ROS node
    NodeFactory factory = new DefaultNodeFactory();
    node = factory.newNode("laser_cmd", configuration);
    // this is defined in the MainDeploy abstract class,
    // calls all deploy methods
    deployAll();
  }

  // automatically called by the programming framework
  @Override  // from super class
  protected void deployRandomMotions(
                  Adder<AbstractRandomMotion>#~#adder)#~#{
    // creates a new instance from class in Listing#~\ref{listing:contextop-implem}#
    // and schedules for deployment
    adder.deploy(new RandomMotion());
  }

  // automatically called by the programming framework
  @Override  // from super class
  protected void deployLaserScans(
                     Adder<AbstractLaserScan>#~#adder)#~#{
    // creates a new instance from class in Listing#~\ref{listing:laserscan-implem}#
    RosLaserScan scan = new RosLaserScan();
    // asks ROS to send laser scan messages to scan
    node.newSubscriber("/ATRV/Sick",
                       "sensor_msgs/LaserScan", scan);
    // schedules for deployment
    adder.deploy(scan);
  }

  // automatically called by the programming framework
  @Override  // from super class
  protected void deployLights(
                         Adder<AbstractLight>#~#adder)#~#{
    // allows the application to send messages to ROS
    Publisher<Bool> rosPublisher;
    rosPublisher = node.newPublisher("/ATRV/LightAct",
				     "std_msgs/Bool");
    // creates a new instance from class in Listing#~\ref{listing:light-implem}#
    RosLight lightPublisher = new RosLight(rosPublisher);
    // schedules for deployment
    adder.deploy(lightPublisher);
  }
}
