package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    public Player(final String name) {
        validatePlayerName(name);
        this.name = name;
    }

    private static void validatePlayerName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어명은 공백이 될 수 없습니다.");
        }
    }

    @Override
    public List<Card> showFirstCards() {
        return Collections.unmodifiableList(cards);
    }
}
