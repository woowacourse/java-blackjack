package blackjack.model.referee;

import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerMatchResult;
import java.util.List;

public class Referee {
    private final Players players;
    private final Dealer dealer;

    public Referee(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<PlayerMatchResult> determinePlayersMatchResult() {
        return players.getPlayers().stream()
                .map(this::determinePlayerMatchResult)
                .toList();
    }

    private PlayerMatchResult determinePlayerMatchResult(final Player player) {
        return new PlayerMatchResult(player.getName(), MatchResult.determineMatchResult(dealer, player));
    }
}
