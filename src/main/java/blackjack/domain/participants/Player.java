package blackjack.domain.participants;

import java.util.Objects;

import blackjack.domain.card.Deck;
import blackjack.exceptions.InvalidPlayerException;

public class Player extends AbstractParticipant {

    public Player(final String name) {
        super();
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (isNullOrEmpty(name)) {
            throw new InvalidPlayerException("빈 칸 또는 null 값이 들어올 수 없습니다.");
        }
    }

    private boolean isNullOrEmpty(final String name) {
        return Objects.isNull(name) || name.trim().isEmpty();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        draw(deck);
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
