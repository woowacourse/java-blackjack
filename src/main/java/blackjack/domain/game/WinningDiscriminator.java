package blackjack.domain.game;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.game.WinningType.BLACKJACK_WIN;
import static blackjack.domain.game.WinningType.DEFEAT;
import static blackjack.domain.game.WinningType.DRAW;
import static blackjack.domain.game.WinningType.WIN;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinningDiscriminator {
    private final Dealer dealer;
    private final List<Player> players;

    public WinningDiscriminator(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return players.stream()
                .collect(toMap(Player::getName, this::judgePlayerResult));
    }

    private WinningType judgePlayerResult(final Player player) {
        if (!player.isScoreBelow(BLACKJACK)) {
            return DEFEAT;
        }
        if (!dealer.isScoreBelow(BLACKJACK)) {
            return WIN;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        return judgePlayerResultByScoreDifference(player);
    }

    private WinningType judgePlayerResultByScoreDifference(final Player player) {
        int scoreDifference = dealer.calculateScoreDifference(player);
        if (scoreDifference > 0) {
            return DEFEAT;
        }
        if (scoreDifference < 0) {
            return WIN;
        }
        return DRAW;
    }
}
