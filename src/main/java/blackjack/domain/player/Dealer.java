package blackjack.domain.player;

public class Dealer extends Player{

  private static final int DRAWABLE_THRESHOLD = 16;

  public boolean isDrawable() {
    return getScore() < DRAWABLE_THRESHOLD;
  }


}
