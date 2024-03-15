package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.score.GameResult;
import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    private final Map<Player, Money> board = new HashMap<>();

    public void bet(Player player, Money money) {
        board.put(player, money);
    }

    public Money findByPlayer(Player player) {
        return board.get(player);
    }

    public void calculateMoney(Map<Player, GameResult> resultBoard) {
        for (Player player : resultBoard.keySet()) {
            GameResult gameResult = resultBoard.get(player);
            Money money = board.get(player);
            Money calculatedMoney = money.multiply(gameResult.getMoneyRate());
            board.replace(player, calculatedMoney);
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
