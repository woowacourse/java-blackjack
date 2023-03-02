package blackjack.domain;

public class Participant {

    private final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }
}
