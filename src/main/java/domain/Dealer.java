package domain;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(final List<Card> cards) {
        super(new Name("딜러"), cards);
    }

    public void fillCards(final Cards cards) {
        while (calculateScore() <= 16) {
            this.receiveCard(cards.getCard());
        }
    }
}
