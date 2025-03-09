package domain;

public class Participant {

  private final Hand hand;

  public Participant() {
    hand = new Hand();
  }

  public Participant(final Hand hand) {
    this.hand = hand;
  }

  public void pickCardOnFirstHandOut(final Deck deck) {
    hit(deck);
    hit(deck);
  }

  public void hit(final Deck deck) {
    final TrumpCard card = deck.draw();
    hand.add(card);
  }

  public int calculateScore() {
    return hand.calculateScore();
  }

  public Hand getCards() {
    return hand;
  }
}

