package domain;

import domain.card.Card;
import java.util.List;
import java.util.Optional;

public class Player extends Participant {

    protected Player(String name) {
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
    protected <T extends Participant> Optional<GameStatus> determineGameStatusWhenBust(final T other) {
        if (isBust()) {
            return Optional.of(GameStatus.LOSE);
        }
        if (other.isBust()) {
            return Optional.of(GameStatus.WIN);
        }
        return Optional.empty();
    }
}
