package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.List;
import java.util.Optional;

public abstract class Participant {

    protected final String name;
    protected final Cards cards;

    protected Participant(String name) {
        validateBlank(name);
        this.name = name;
        this.cards = new Cards();
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean ableToAddCard();

    protected abstract <T extends Participant> Optional<GameStatus> determineGameStatusWhenBust(T other);

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public GameStatus determineGameStatus(Participant other) {
        Optional<GameStatus> gameStatus = determineGameStatusWhenBust(other);
        return gameStatus.orElseGet(() -> determineGameStatusByScore(other));
    }

    private GameStatus determineGameStatusByScore(final Participant other) {
        if (cards.calculateScore() > other.cards.calculateScore()) {
            return GameStatus.WIN;
        }
        if (cards.calculateScore() < other.cards.calculateScore()) {
            return GameStatus.LOSE;
        }
        return GameStatus.TIE;
    }

    public boolean isBust() {
        return cards.isBust(cards.calculateScore());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new ErrorException("참여자 이름은 공백일 수 없습니다.");
        }
    }
}
