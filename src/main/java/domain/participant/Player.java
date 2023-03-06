package domain.participant;

import domain.game.GamePoint;
import domain.card.Cards;

public final class Player extends Participant {

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
            throw new IllegalArgumentException(
                    String.format("'%s'라는 이름을 가질 수 없습니다.", DEALER_NAME));
        }
    }

    public boolean hasLowerThan(final GamePoint gamePoint) {
        return calculatePoint().isLowerThan(gamePoint);
    }

    public boolean hasSameAs(final GamePoint gamePoint) {
        return calculatePoint().isSameAs(gamePoint);
    }

    public boolean hasGreaterThan(final GamePoint gamePoint) {
        return calculatePoint().isGreaterThan(gamePoint);
    }
}
