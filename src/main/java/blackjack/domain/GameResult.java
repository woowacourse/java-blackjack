package blackjack.domain;

import java.util.function.IntUnaryOperator;

public enum GameResult {
    WIN("승", IntUnaryOperator.identity()),
    LOSE("패", money -> -money),
    DRAW("무", money -> 0),
    BLACKJACK("블랙잭", money -> (int) (money * 1.5));

    private final String name;
    private final IntUnaryOperator intUnaryOperator;

    GameResult(String name, IntUnaryOperator intUnaryOperator) {
        this.name = name;
        this.intUnaryOperator = intUnaryOperator;
    }

    public String getName() {
        return name;
    }

    public int applyBet(int money) {
        return intUnaryOperator.applyAsInt(money);
    }
}
