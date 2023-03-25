package blackjack.view.dto;

import blackjack.domain.participant.Player;

public class PlayerResultResponse {

    private final String name;
    private final int profit;

    public PlayerResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static PlayerResultResponse of(final Player player, final int profit) {
        return new PlayerResultResponse(player.getName(), profit);
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
