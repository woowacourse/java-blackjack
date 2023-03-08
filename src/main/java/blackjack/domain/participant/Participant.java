package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

final public class Participant {

    public static final int INIT_CARD_COUNT = 2;

    private final String name;
    private final Cards cards;

    public Participant(final String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void receiveCard(final Card card) {
        this.cards.add(card);
    }

    public int calculateTotalScore() {
        return this.cards.calculateTotalScore();
    }

    public boolean isBust() {
        return this.cards.isBust();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
