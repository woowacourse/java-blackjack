package blackjack.domain.judgement;

import java.util.List;
import java.util.Objects;

public enum GameResult {

    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0),
    LOSE("패", -1.0);

    private final String name;
    private final double dividendRate;

    GameResult(String name, double dividendRate) {
        this.name = name;
        this.dividendRate = dividendRate;
    }

    public String getName() {
        return name;
    }

    private static List<GameResult> all() {
        return List.of(values());
    }

    public static GameResult pick(String name) {
        return all().stream()
            .filter(gameResult -> Objects.equals(name, gameResult.name))
            .findFirst()
            .orElse(DRAW);
    }

    public double calculateProfit(BettingMoney bettingMoney) {
        return bettingMoney.multiply(dividendRate);
    }
}
