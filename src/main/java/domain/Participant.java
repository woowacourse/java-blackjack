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
    pickCard(deck);
    pickCard(deck);
  }

  public void pickCard(final Deck deck) {
    final TrumpCard card = deck.pickCard();
    hand.add(card);
  }

  public int calculateScore() {
    return hand.calculateScore();
  }

  public Hand getCards() {
    return hand;
  }
}

