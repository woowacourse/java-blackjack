package blackjack.domain;

import java.util.Objects;

public class Player extends Participant {
    private final Name name;

    private Player(Name name, Hand hand) {
        super(hand);
        validateNameNotNull(name);
        this.name = name;
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init());
    }

    private void validateNameNotNull(Name name) {
        Objects.requireNonNull(name, "name 은 null 이 올 수 없습니다.");
    }

    public String name() {
        return name.getName();
    }

    public boolean canHit() {
        return score() <= BLACKJACK_THRESHOLD;
    }
}