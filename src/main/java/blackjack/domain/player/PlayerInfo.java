package blackjack.domain.player;

import blackjack.domain.card.CardBundle;
import blackjack.domain.generic.BettingMoney;

public class PlayerInfo {
    private final String name;
    private final BettingMoney bettingMoney;

    public PlayerInfo(String name, BettingMoney bettingMoney) {
        validate(name, bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validate(String name, BettingMoney bettingMoney) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 비었습니다.");
        }
        if (bettingMoney == null) {
            throw new IllegalArgumentException("베팅 금액이 비었습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public Player toEntity() {
        return new Gambler(CardBundle.emptyBundle(), this);
    }

    public double getBettingMoney() {
        return bettingMoney.getMoney();
    }

}
