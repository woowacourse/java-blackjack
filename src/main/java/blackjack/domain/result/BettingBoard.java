package blackjack.domain.result;

import blackjack.domain.player.PlayerName;
import blackjack.domain.score.GameResult;
import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    private final Map<PlayerName, Money> board = new HashMap<>();

    public void bet(PlayerName playerName, Money money) {
        board.put(playerName, money);
    }

    public Money findByPlayerName(PlayerName playerName) {
        return board.get(playerName);
    }

    public void calculateMoney(Map<PlayerName, GameResult> resultBoard) {
        for (PlayerName playerName : resultBoard.keySet()) {
            GameResult gameResult = resultBoard.get(playerName);
            Money money = board.get(playerName);
            Money calculatedMoney = money.multiply(gameResult.getMoneyRate());
            board.replace(playerName, calculatedMoney);
        }
    }

    public Money calculateDealerMoney() {
        int playerMoneySum = board.values().stream()
                .mapToInt(Money::money)
                .sum();
        Money playersIncome = new Money(playerMoneySum);
        return playersIncome.multiply(-1);
    }
}
