// Implementation of Light from Listing#~\ref{listing:design}# line#~\ref{design:light}#
public class RosLight extends AbstractLight {

  // A ROS publisher to communicate with the robot
  private final Publisher<Bool> publisher;

  public RosLight(Publisher<Bool> publisher) {
    this.publisher = publisher;
    publish(false);
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:on}#
  @Override
  protected void on() throws Exception {
    // update the isStarted attribute from
    // Listing#~\ref{listing:design}# line#~\ref{design:isStarted}#
    updateIsStarted(true);
    publish(true);
  }

  // required by design in Listing#~\ref{listing:design}# line#~\ref{design:off}#
  @Override
  protected void off() throws Exception {
    updateIsStarted(false);
    publish(false);
  }

  // turns on or off the light depending on the parameter
  private void publish(boolean val) {
    // converts the Java type boolean to the ROS type BOOL
    Bool bool = new Bool();
    bool.data = val;
    // ask the robot to trigger its light projector
    publisher.publish(bool);
  }

}