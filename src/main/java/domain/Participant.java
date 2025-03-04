package domain;

import domain.card.Card;

public abstract class Participant {

    protected final String name;
    protected final Cards cards;

    protected Participant(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    abstract boolean ableToAddCard();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public GameStatus determineGameStatus(Participant participant) {
        return cards.determineGameStatus(participant.cards);
    }
}
