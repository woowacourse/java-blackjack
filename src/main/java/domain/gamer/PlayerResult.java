package domain.gamer;

import java.util.Arrays;

public enum PlayerResult {
    BLACKJACKWIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    final double ratio;

    PlayerResult(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return Arrays.stream(PlayerResult.values())
                .filter(value -> value == this)
                .findFirst()
                .map(result -> result.ratio)
                .orElse(0.0);
    }
}
