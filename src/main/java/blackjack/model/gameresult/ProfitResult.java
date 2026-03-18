package blackjack.model.gameresult;

import blackjack.model.user.User;
import java.util.Map;

public record ProfitResult(Map<User, Integer> playersProfit, int dealerProfit) {
}
