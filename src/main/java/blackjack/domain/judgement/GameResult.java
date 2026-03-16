package blackjack.domain.judgement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public enum GameResult {

    BLACKJACK("블랙잭", BigDecimal.valueOf(1.5)),
    WIN("승", BigDecimal.valueOf(1.0)),
    DRAW("무", BigDecimal.valueOf(0)),
    LOSE("패", BigDecimal.valueOf(-1.0));

    private final String name;
    private final BigDecimal dividendRate;

    GameResult(String name, BigDecimal dividendRate) {
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

    public BigDecimal calculateProfit(BettingMoney bettingMoney) {
        return bettingMoney.toBigDecimal().multiply(dividendRate);
    }
}
