package blackjack.view.dto;

import blackjack.domain.player.PlayerInfo;
import blackjack.generic.BettingMoney;

public class NameMoneyDTO {
    private final String name;
    private final BettingMoney bettingMoney;

    public NameMoneyDTO(String name, double money) {
        this.name = name;
        this.bettingMoney = BettingMoney.of(money);
    }

    public String getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public PlayerInfo toEntity() {
        return new PlayerInfo(name, bettingMoney);
    }
}
