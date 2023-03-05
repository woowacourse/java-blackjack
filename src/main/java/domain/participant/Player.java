package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    private Player(final Name name) {
        super(name);
    }

    private Player(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Player of(final Name name) {
        validateName(name);
        return new Player(name);
    }

    public static Player create(final Name name, final Cards cards) {
        validateName(name);
        return new Player(name, cards);
    }

    private static void validateName(final Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException("'딜러'라는 이름을 가질 수 없습니다.");
        }
    }
}
