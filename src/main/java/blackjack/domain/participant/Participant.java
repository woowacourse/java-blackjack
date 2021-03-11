package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class Participant {
    protected final Cards cards = new Cards();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int cardResult() {
        return cards.calculateResult();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract List<Card> getOpenCard();

    public abstract String getName();
}


