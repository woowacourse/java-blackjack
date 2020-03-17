package blackjack.player.domain.report;

import blackjack.card.domain.GameResult;
import blackjack.generic.BettingMoney;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameReport that = (GameReport) o;
        return Objects.equals(name, that.name) &&
                gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameResult);
    }
}
