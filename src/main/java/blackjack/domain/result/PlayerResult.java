package blackjack.domain.result;

import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

public class PlayerResult {
    // todo Dto로 안만드려면 얘한테도 책임을 줘야할텐데...

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
