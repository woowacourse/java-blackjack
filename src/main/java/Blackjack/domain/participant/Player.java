package Blackjack.domain.participant;

import Blackjack.domain.card.Card;
import Blackjack.domain.game.GameStatus;
import java.util.List;
import java.util.Optional;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }

    @Override
    public boolean ableToAddCard() {
        return !isBust();
    }

    @Override
    protected Optional<GameStatus> determineGameStatusWhenBust(final Participant other) {
        if (isBust()) {
            return Optional.of(GameStatus.LOSE);
        }
        if (other.isBust()) {
            return Optional.of(GameStatus.WIN);
        }
        return Optional.empty();
    }
}
