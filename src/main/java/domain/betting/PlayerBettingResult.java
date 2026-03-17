package domain.betting;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public record PlayerBettingResult
        (Name playerName,
         BettingRule bettingRule) {

    public static PlayerBettingResult from(Dealer dealer, Player player) {
        BettingRule bettingRule = BettingRule.determine(dealer, player);

        return new PlayerBettingResult(player.getName(), bettingRule);
    }
}

