// Implementation of Light from Listing#~\ref{listing:design}# line#~\ref{design:light-b}#
public class RosLight extends AbstractLight {

  // A ROS publisher to communicate with the robot
  private final Publisher<Bool> publisher;

  public RosLight(Publisher<Bool> publisher) {
    this.publisher = publisher;
    publish(false);
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:on}# and line#~\ref{design:light-onoff}#
  @Override // from super class
  protected void on() throws Exception {
    publish(true);
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:off}# and line#~\ref{design:light-onoff}#
  @Override // from super class
  protected void off() throws Exception {
    publish(false);
  }

  // turns on or off the light depending on the parameter
  private void publish(boolean val) {
    // converts the Java type boolean to the ROS type Bool
    Bool bool = new Bool();
    bool.data = val;
    // asks the robot to trigger its light projector
    publisher.publish(bool);
  }
}
