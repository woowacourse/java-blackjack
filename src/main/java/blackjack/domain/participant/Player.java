package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {
    private Player(final String name, final List<Card> cards) {
        super(name, cards);
    }

    public static Player newInstance(final String name, final List<Card> cards) {
        Objects.requireNonNull(name, "이름에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        return new Player(name, cards);
    }

    private static void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이 들어올 수 없습니다.");
        }
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    @Override
    public List<Card> getCards() {
        return state.cards().values();
    }
}
