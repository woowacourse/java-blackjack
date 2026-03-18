package domain.betting;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public record PlayerMatchResult
        (Name playerName,
         MatchResult matchResult) {

    public static PlayerMatchResult from(Dealer dealer, Player player) {
        MatchResult matchResult = MatchResult.determine(dealer, player);

        return new PlayerMatchResult(player.getName(), matchResult);
    }
}

