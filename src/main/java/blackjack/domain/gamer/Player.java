package blackjack.domain.gamer;

import blackjack.domain.rule.CardCalculator;

import java.util.Objects;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean canDrawCard() {
        return calculateSum() <= CardCalculator.BUST_THRESHOLD;
    }

    @Override
    public String getName() {
        return name;
    }
}