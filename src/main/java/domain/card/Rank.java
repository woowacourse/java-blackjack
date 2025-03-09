package domain.card;

public enum Rank {
  ACE(11),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(10),
  QUEEN(10),
  KING(10);

  private static final int ACE_MIN = 1;
  private static final int BURST_SCORE = 21;
  private final int score;

  Rank(final int score) {
    this.score = score;
  }

  public static int ifBustAceIsMIN(int score, int aceCount) {
    while (score > BURST_SCORE && aceCount-- > 0) {
      score -= ACE.score;
      score += ACE_MIN;
    }
    return score;
  }

  public int add(final int score) {
    return this.score + score;
  }
}
