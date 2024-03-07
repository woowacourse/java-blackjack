package blackjack.domain;

public class Dealer extends Player {

    public Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public Card getFirstCard() {
        return hand.getAllCards().get(0);
    }
}
