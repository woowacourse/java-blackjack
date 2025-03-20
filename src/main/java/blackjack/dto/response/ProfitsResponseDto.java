package blackjack.dto.response;

import java.util.List;
import java.util.Map;

public record ProfitsResponseDto(
        List<PlayerProfit> profits
) {

    public static ProfitsResponseDto of(Map<String, Double> dealerProfit, Map<String, Double> playerProfits) {
        dealerProfit.putAll(playerProfits);
        return new ProfitsResponseDto(dealerProfit.entrySet().stream()
                .map((entry) -> new PlayerProfit(entry.getKey(), entry.getValue()))
                .toList());
    }

    public record PlayerProfit(
            String name,
            double profit
    ) {

        @Override
        public String toString() {
            return String.format("%s: %d",
                    name,
                    (int) profit);
        }
    }
}
