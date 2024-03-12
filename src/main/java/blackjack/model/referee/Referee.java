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
        return new PlayerMatchResult(player.getName(), determineMatchResult(player));
    }

    private MatchResult determineMatchResult(final Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return MatchResult.PUSH;
        }
        if (dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }
        if (player.isBlackJack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return determineMatchResultByTotalScore(player);
    }

    private MatchResult determineMatchResultByTotalScore(final Player player) {
        int playerTotal = player.calculateCardsTotalScore();
        int dealerTotal = dealer.calculateCardsTotalScore();
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust() || playerTotal > dealerTotal) {
            return MatchResult.WIN;
        }
        if (playerTotal == dealerTotal) {
            return MatchResult.PUSH;
        }
        return MatchResult.LOSE;
    }
}
