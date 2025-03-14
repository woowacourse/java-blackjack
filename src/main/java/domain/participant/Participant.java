package domain.participant;

import domain.Cards;
import domain.GameStatus;
import domain.card.Card;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Cards cards;

    protected Participant(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean ableToAddCard();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public GameStatus determineGameStatus(Participant participant) {
        return cards.determineGameStatus(participant.cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardsScore() {
        return cards.calculateScore();
    }
}
