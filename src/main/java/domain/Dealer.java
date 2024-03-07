package domain;

import static domain.BlackJackGame.DEALER_THRESHOLD;

public class Dealer extends Participant {

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Cards deck;

    public Dealer(final Cards cards) {
        super(DEFAULT_DEALER_NAME);
        deck = cards;
    }

    public Card peek() {
        return hand.peek();
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffle(){
        deck.shuffle();
    }

    public void deal(final Participant participant) {
        participant.receive(drawCard());
    }

    public boolean doesGetCard(){
        return cardSum() <= DEALER_THRESHOLD;
    }
}
