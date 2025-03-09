package domain;

public class Dealer {

  private static final int STAND_SCORE = 17;
  private final Participant participant;
  private int winCount = 0;
  private int loseCount = 0;

  public Dealer() {
    this.participant = new Participant();
  }

  public Dealer(final Hand hand) {
    this.participant = new Participant(hand);
  }

  public boolean isHit() {
    return participant.calculateScore() < STAND_SCORE;
  }

  public void initialDeal(final Deck deck) {
    participant.pickCardOnFirstHandOut(deck);
  }

  public void hit(final Deck deck) {
    participant.hit(deck);
  }

  public void round(final Player player) {
    player.round(participant);
    final boolean isWinPlayerDuelResult = player.isWinDuel();
    if (isWinPlayerDuelResult) {
      loseCount++;
      return;
    }
    winCount++;
  }

  public Participant getParticipant() {
    return participant;
  }

  public int getWinCount() {
    return winCount;
  }

  public int getLoseCount() {
    return loseCount;
  }
}
