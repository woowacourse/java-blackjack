package blackjack.domain.gamer;

import blackjack.domain.rule.HandCalculator;

import static blackjack.domain.rule.Score.SCORES;

import java.util.Objects;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean canDrawCard() {
        return !handScore().isBusted();
    }

    @Override
    public String getName() {
        return name;
    }
}