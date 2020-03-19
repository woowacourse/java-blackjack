package blackjack.domain.gamer;

import blackjack.domain.rule.HandCalculator;

import static blackjack.domain.rule.Score.SCORES;

import java.util.Objects;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDrawCard() {
        return !handScore().isBusted();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}