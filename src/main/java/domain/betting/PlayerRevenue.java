package domain.betting;

import domain.participant.PlayerName;

public record PlayerRevenue(PlayerName playerName, Revenue revenue) {

    public PlayerRevenue revenueReverse() {
        return new PlayerRevenue(playerName, revenue.reverse());
    }

    public int revenueValue() {
        return revenue.value();
    }

    public boolean isSamePlayerName(PlayerName otherPlayerName) {
        return playerName.equals(otherPlayerName);
    }
}
