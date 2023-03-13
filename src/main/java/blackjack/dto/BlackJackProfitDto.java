package blackjack.dto;

import java.util.List;

public class BlackJackProfitDto {

    private final int dealerProfit;
    private final List<PlayerProfitDto> playerProfit;

    public BlackJackProfitDto(final int dealerProfit, final List<PlayerProfitDto> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public List<PlayerProfitDto> getPlayerProfit() {
        return playerProfit;
    }
}
