package blackjack.domain.report;

import blackjack.domain.generic.BettingMoney;
import blackjack.domain.result.GameResult;

public class GameReport {
    private final String name;
    private final double money;
    private final GameResult gameResult;

    public GameReport(String name, BettingMoney bettingMoney, GameResult gameResult) {
        validate(name, gameResult);
        this.name = name;
        this.money = bettingMoney.getMoney();
        this.gameResult = gameResult;
    }

    private void validate(String name, GameResult gameResult) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if (gameResult == null) {
            throw new IllegalArgumentException("게임 결과가 비어있습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return this.money;
    }

    public boolean isNotDraw() {
        return this.gameResult != GameResult.DRAW;
    }

}
