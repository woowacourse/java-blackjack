package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Gamer {

    protected final Cards cards;

    public Gamer(final Cards cards) {
        this.cards = cards;
    }

    abstract public boolean canGetMoreCard();

    abstract public String getNickname();

    public List<Card> showAllCard() {
        return cards.getCards();
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
    }
}
