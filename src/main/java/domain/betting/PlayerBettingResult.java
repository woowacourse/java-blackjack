package domain.betting;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public record PlayerBettingResult
        (Name playerName,
         MatchingRule bettingRule) {

    public static PlayerBettingResult from(Dealer dealer, Player player) {
        MatchingRule bettingRule = MatchingRule.determine(dealer, player);

        return new PlayerBettingResult(player.getName(), bettingRule);
    }
}

