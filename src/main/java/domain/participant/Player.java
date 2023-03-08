package domain.participant;

import domain.game.GamePoint;
import domain.card.Cards;

public final class Player extends Participant {

    protected Player(final Name name, final int bet) {
        super(name, bet);
    }

    protected Player(final Name name, final Cards cards, final int bet) {
        super(name, cards, bet);
    }

    public static Player of(final Name name, final int bet) {
        validateName(name);
        return new Player(name, bet);
    }

    public static Player create(final Name name, final Cards cards, final int bet) {
        validateName(name);
        return new Player(name, cards, bet);
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
