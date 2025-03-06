package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Cards cards;

    protected Participant(String name) {
        validateBlank(name);
        this.name = name;
        this.cards = new Cards();
    }

    public abstract boolean ableToAddCard();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isParticipant(String name) {
        return this.name.equals(name);
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

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new ErrorException("참여자 이름은 공백일 수 없습니다.");
        }
    }
}
