package domain.betting;

import domain.betting.exception.BettingException;
import domain.betting.exception.ErrorMessage;
import domain.gamer.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Profits {

    private static final int PREFIX_INITIAL_PROFIT = 0;

    private final Map<PlayerName, Profit> profitByPlayer;

    public Profits() {
        this.profitByPlayer = new HashMap<>();
    }

    public void settleProfit(PlayerName playerName, Profit profit) {
        validateDuplicatedProfit(playerName);
        profitByPlayer.put(playerName, profit);
    }

    public Profit getProfit(PlayerName playerName) {
        validateDuplicatedProfit(playerName);
        return profitByPlayer.get(playerName);
    }

    public Profit calculateDealerProfit() {
        return profitByPlayer.values()
                .stream()
                .reduce(new Profit(PREFIX_INITIAL_PROFIT), Profit::addProfit)
                .reverseProfit();
    }

    private void validateDuplicatedProfit(PlayerName playerName) {
        if (profitByPlayer.containsKey(playerName)) {
            throw new BettingException(ErrorMessage.DUPLICATED_PLAYER_PROFIT);
        }
    }

}
