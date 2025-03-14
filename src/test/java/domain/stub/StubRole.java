package domain.stub;

import domain.Bet;
import domain.card.Score;
import domain.participant.Role;

public class StubRole implements Role {

  @Override
  public boolean isHit(Score score) {
    return true;
  }

  @Override
  public String getName() {
    return "STUB";
  }

  @Override
  public Bet getBet() {
    return new Bet(1000);
  }
}
