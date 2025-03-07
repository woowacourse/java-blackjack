package blackjack.model;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Participant {

    private final String name;
    private final HitDecisionStrategy hitDecisionStrategy;

    public Player(String name, HitDecisionStrategy hitDecisionStrategy) {
        super(new ArrayList<>());
        this.name = name;
        this.hitDecisionStrategy = hitDecisionStrategy;
    }

    @Override
    public boolean shouldHit() {
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
