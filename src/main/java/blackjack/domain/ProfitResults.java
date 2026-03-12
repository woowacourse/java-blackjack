package blackjack.domain;

import java.util.Map;

public record ProfitResults(Double dealerProfit, Map<Player, Double> playerProfit) {
}
