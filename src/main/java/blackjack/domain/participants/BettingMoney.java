package blackjack.domain.participants;


import java.util.Map;

public record BettingMoney(
        int amount
) {
    public BettingMoney {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금액은 1000원 단위여야합니다.");
        }
    }

    public static record Profit(
            int dealerProfit,
            Map<Player, Integer> playerProfit
    ) {
        public Profit(int dealerProfit, Map<Player, Integer> playerProfit) {
            this.dealerProfit = dealerProfit;
            this.playerProfit = playerProfit;
        }
    }
}
