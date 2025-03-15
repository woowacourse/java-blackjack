package domain.card;

public record Score(int value) {

  private static final Score BLACKJACK_SCORE = new Score(21);
  private static final Score ADDITIONAL_ACE_SCORE = new Score(10);
  private static final Score DEALER_STAY_THRESHOLD = new Score(17);
  private static final int BLACKJACK_HAND_COUNT = 2;

  public Score withAce() {
    final Score maximumAce = this.add(ADDITIONAL_ACE_SCORE);
    if (maximumAce.isGreaterThan(BLACKJACK_SCORE)) {
      return this;
    }
    return maximumAce;
  }

  public boolean isBust() {
    return this.isGreaterThan(BLACKJACK_SCORE);
  }

  public boolean isDealerNeedHit() {
    return DEALER_STAY_THRESHOLD.isGreaterThan(this);
  }

  public boolean isBlackjack(final int handCount) {
    return handCount == BLACKJACK_HAND_COUNT & equals(BLACKJACK_SCORE);
  }

  public boolean isGreaterThan(final Score other) {
    return this.value > other.value;
  }

  public Score add(Score other) {
    return new Score(this.value + other.value);
  }
}
