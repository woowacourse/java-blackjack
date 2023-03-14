package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private static final String BAN_NAME = "딜러";

    private final Name name;

    public Player(Name name) {
        super();
        validate(name);
        this.name = name;
    }

    private void validate(Name name) {
        if (BAN_NAME.equals(name.getValue())) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
    }

    @Override
    public boolean canReceive() {
        return calculateCurrentScore() < BLACK_JACK_SCORE;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
