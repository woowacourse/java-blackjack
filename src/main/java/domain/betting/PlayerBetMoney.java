package domain.betting;

import domain.participant.PlayerName;

public class PlayerBetMoney {
    private final PlayerName playerName;
    private final Money money;

    public PlayerBetMoney(PlayerName playerName, Money money) {
        this.playerName = playerName;
        this.money = money;
    }

    public boolean isSameName(PlayerName otherPlayerName) {
        return this.playerName.equals(otherPlayerName);
    }

    public int getMoneyValue() {
        return money.value();
    }
}
