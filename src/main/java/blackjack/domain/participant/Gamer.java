package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public abstract class Gamer {

    protected final Cards cards;

    public Gamer(final Cards cards) {
        this.cards = cards;
    }

    public abstract Cards showInitialCards();

    public abstract boolean canGetMoreCard();

    public abstract String getNickname();

    public void receiveCards(final Cards givenCards) {
        cards.addAll(givenCards);
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
    }

    public Cards showAllCards() {
        return cards;
    }
}
