package blackjack.domain.result;

import blackjack.domain.playing.user.Player;

import java.util.Map;

public class BettingResult {
    private Map<Player, BettingMoney> bettingResult;

    public BettingResult(Map<Player, BettingMoney> bettingResult) {
        this.bettingResult = bettingResult;
    }
}
