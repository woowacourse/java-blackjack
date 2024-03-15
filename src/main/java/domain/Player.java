package domain;

import java.util.Objects;

import static domain.Dealer.DEALER_NAME;

public class Player extends Participant {

    static final int MAX_SCORE_TO_HIT = 21;
    static final String DEALER_NAME_MESSAGE = "딜러라는 이름은 사용할 수 없습니다.";

    Player(String name) {
        super(validateNameNotEqualToDealer(name));
    }

    private static String validateNameNotEqualToDealer(String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(DEALER_NAME_MESSAGE);
        }
        return name;
    }

    public boolean isHittable() {
        return getTotalScore() < MAX_SCORE_TO_HIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
