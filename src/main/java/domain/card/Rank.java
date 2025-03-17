package domain.card;

public enum Rank {
  ACE(new Score(1)),
  TWO(new Score(2)),
  THREE(new Score(3)),
  FOUR(new Score(4)),
  FIVE(new Score(5)),
  SIX(new Score(6)),
  SEVEN(new Score(7)),
  EIGHT(new Score(8)),
  NINE(new Score(9)),
  TEN(new Score(10)),
  JACK(new Score(10)),
  QUEEN(new Score(10)),
  KING(new Score(10));

  private final Score score;

  Rank(final Score score) {
    this.score = score;
  }

  public Score getScore() {
    return score;
  }
}
