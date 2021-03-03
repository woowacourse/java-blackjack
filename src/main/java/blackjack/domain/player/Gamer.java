package blackjack.domain.player;

import blackjack.util.BlackjackScoreCalculator;
import blackjack.util.ScoreCalculator;

public class Gamer extends Player {

  public Gamer(String name, ScoreCalculator scoreCalculator) {
    super(name, scoreCalculator);
  }
  public Gamer(String name) {
    this(name, new BlackjackScoreCalculator());
  }

  @Override
  public boolean isDrawable() {
    throw new UnsupportedOperationException();
  }


}
