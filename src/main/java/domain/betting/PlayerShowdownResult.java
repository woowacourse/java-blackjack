package domain.betting;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public record PlayerShowdownResult
        (Name playerName,
         MatchResult matchResult) {

    public static PlayerShowdownResult from(Dealer dealer, Player player) {
        MatchResult matchResult = MatchResult.resolve(dealer, player);

        return new PlayerShowdownResult(player.getName(), matchResult);
    }
}

