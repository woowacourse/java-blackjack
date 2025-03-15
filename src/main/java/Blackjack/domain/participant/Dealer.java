package Blackjack.domain.participant;

import Blackjack.domain.card.Card;
import Blackjack.domain.game.GameStatus;
import java.util.List;
import java.util.Optional;

public class Dealer extends Participant {

    private static final int ADD_CARD_UPPER_BOUND = 16;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        final List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        final int cardsScore = cards.calculateScore();
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
