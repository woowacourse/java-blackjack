package blackjack.model.referee;

import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerMatchResult;

import java.util.List;

public class Referee {
    private final Dealer dealer;

    public Referee(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<PlayerMatchResult> determinePlayersMatchResult(final Players players) {
        return players.getPlayers().stream()
                .map(this::determinePlayerResult)
                .toList();
    }

    private PlayerMatchResult determinePlayerResult(final Player player) {
        return new PlayerMatchResult(player.getName(), determineMatchResult(player));
    }

    MatchResult determineMatchResult(final Player player) {
        int playerTotal = player.calculateCardsTotal();
        int dealerTotal = dealer.calculateCardsTotal();

        if (dealer.isBlackJack() && player.isBlackJack()) {
            return MatchResult.TIE;
        }
        if (dealer.isBurst()) {
            return MatchResult.WIN;
        }
        if (player.isBurst()) {
            return MatchResult.LOSE;
        }
        if (playerTotal >= dealerTotal) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
