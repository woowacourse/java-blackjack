package blackjack.domain.result;

import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

public class PlayerResultDto {

    private final String name;
    private final int winningMoney;

    public PlayerResultDto(String name, int winningMoney) {
        this.name = name;
        this.winningMoney = winningMoney;
    }

    public static PlayerResultDto of(Player player, Money earningMoney) {
        return new PlayerResultDto(player.getName(), earningMoney.toInt());
    }

    public String getName() {
        return name;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
