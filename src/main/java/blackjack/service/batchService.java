package blackjack.service;

import blackjack.domain.PlayerResult;
import blackjack.domain.user.Bet;
import blackjack.domain.user.Player;
import blackjack.dto.UserProfitDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class batchService {

    public static UserProfitDto calculate(Map<Player, PlayerResult> statistics) {

        double dealerProfit= 0;
        Map<String, Double> info = new LinkedHashMap<>();
        for (Map.Entry<Player, PlayerResult> entry : statistics.entrySet()) {
            Player player = entry.getKey();
            Bet bet = player.getBet();
            PlayerResult result = entry.getValue();
            double calculate = bet.calculate(result.getProfit());
            dealerProfit+=calculate;
            info.put(player.getName(), calculate);
        }

        return new UserProfitDto(-dealerProfit, info);
    }
}
