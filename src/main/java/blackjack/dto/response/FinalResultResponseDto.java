package blackjack.dto.response;

import blackjack.util.ListMerger;

import java.util.LinkedHashMap;
import java.util.List;

public record FinalResultResponseDto(
        List<InnerGamer> gamers
) {

    public static FinalResultResponseDto of(double dealerProfit, LinkedHashMap<String, Double> playerProfits) {
        InnerGamer dealer = new InnerGamer("딜러", dealerProfit);
        List<InnerGamer> players = playerProfits.entrySet().stream()
                .map((entry) -> new InnerGamer(entry.getKey(), entry.getValue()))
                .toList();
        return new FinalResultResponseDto(ListMerger.combine(dealer, players));
    }

    public record InnerGamer(
            String name,
            double profit
    ) {

        @Override
        public String toString() {
            return String.format("%s: %f",
                    name,
                    profit);
        }
    }
}
