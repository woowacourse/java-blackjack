package domain;

import domain.card.Card;
import java.util.List;
import java.util.Optional;

public class Dealer extends Participant {

    private static final int ADD_CARD_UPPER_BOUND = 16;

    protected Dealer(String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= ADD_CARD_UPPER_BOUND;
    }

    @Override
    protected <T extends Participant> Optional<GameStatus> determineGameStatusWhenBust(final T other) {
        if (other.isBust()) {
            return Optional.of(GameStatus.WIN);
        }
        if (isBust()) {
            return Optional.of(GameStatus.LOSE);
        }
        return Optional.empty();
    }
}
