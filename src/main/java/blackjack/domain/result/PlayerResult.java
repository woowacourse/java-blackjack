package blackjack.domain.result;

import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

public class PlayerResult {

    private final String name;
    private final int winningMoney;

    public PlayerResult(String name, int winningMoney) {
        this.name = name;
        this.winningMoney = winningMoney;
    }

    public static PlayerResult of(Player player, Money earningMoney) {
        return new PlayerResult(player.getName(), earningMoney.toInt());
    }

    public String getName() {
        return name;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
