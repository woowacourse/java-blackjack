package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Gamer {

    protected final Cards cards;

    public Gamer(final Cards cards) {
        this.cards = cards;
    }

    public abstract boolean canGetMoreCard();

    public abstract String getNickname();

    public List<Card> showAllCard() {
        return cards.getCards();
    }

    public void receiveCards(final Cards givenCards) {
        cards.addAll(givenCards);
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
    }
}
