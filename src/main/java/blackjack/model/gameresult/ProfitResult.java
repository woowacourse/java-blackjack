package blackjack.model.gameresult;

import blackjack.model.user.Player;
import java.util.Map;

public record ProfitResult(Map<Player, Integer> playersProfit, int dealerProfit) {
}
