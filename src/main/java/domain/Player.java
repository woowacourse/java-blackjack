package domain;

import java.util.List;
import java.util.Objects;

import static domain.Dealer.DEALER_NAME;

public class Player extends Participant {

    static final int MAX_SCORE_TO_HIT = 21;
    static final String DEALER_NAME_MESSAGE = String.format("%s라는 이름은 사용할 수 없습니다.", DEALER_NAME);

    public Player(String name) {
        super(validateNameNotEqualToDealer(name));
    }

    Player(List<Card> cards) {
        super("플레이어");
        this.hand.add(cards);
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
