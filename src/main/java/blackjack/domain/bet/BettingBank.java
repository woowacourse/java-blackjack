package blackjack.domain.bet;

import blackjack.domain.rule.BetResult;
import blackjack.domain.rule.GameResult;

public class BettingBank {

    public BetResult calculateBetResult(Bet bet, GameResult gameResult) {
        Money earned = bet.getBetAmount().multiply(gameResult.getLeverage());
        return new BetResult(bet.getPlayerName(), earned);
    }
}
