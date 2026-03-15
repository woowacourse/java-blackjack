package blackjack.domain;

import java.math.BigDecimal;
import java.util.Map;

public record ProfitResults(BigDecimal dealerProfit, Map<Player, BigDecimal> playerProfit) {
}