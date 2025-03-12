package mock;

import domain.participant.Participant;

public class MockParticipant extends Participant {

  public MockParticipant() {
    super();
  }

  @Override
  public boolean isHit() {
    return true;
  }

  @Override
  public boolean isDealer() {
    return true;
  }

  @Override
  public String getName() {
    return "MOCK";
  }
}
