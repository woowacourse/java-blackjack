package blackjack.domain.result;

import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

public class PlayerResult {

    private final String name;
    private final Money winningMoney;

    public PlayerResult(String name, Money winningMoney) {
        this.name = name;
        this.winningMoney = winningMoney;
    }

    public static PlayerResult from(Player player, Money earningMoney) {
        return new PlayerResult(
                player.getName(),
                earningMoney);
    }

    public String getName() {
        return name;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }
}
