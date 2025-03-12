package blackjack.model.participant;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        super(new ArrayList<>());
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.isBlank() || name == null) {
            throw new IllegalArgumentException("이름은 한글자 이상이어야합니다.");
        }
    }

    public boolean shouldHit(HitDecisionStrategy hitDecisionStrategy) {
        return hitDecisionStrategy.decideHit(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
