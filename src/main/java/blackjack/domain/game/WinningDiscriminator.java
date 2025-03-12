package blackjack.domain.game;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.game.WinningType.BLACKJACK_WIN;
import static blackjack.domain.game.WinningType.DEFEAT;
import static blackjack.domain.game.WinningType.DRAW;
import static blackjack.domain.game.WinningType.WIN;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.gambler.Gambler;
import blackjack.domain.gambler.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WinningDiscriminator {
    private final Gambler dealer;
    private final List<Gambler> players;

    public WinningDiscriminator(final Gambler dealer, final List<Gambler> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public Map<Name, WinningType> judgePlayersResult() {
        return players.stream()
                .collect(toMap(Gambler::getName, this::judgePlayerResult));
    }

    private WinningType judgePlayerResult(final Gambler player) {
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

    private WinningType judgePlayerResultByScoreDifference(final Gambler player) {
        int scoreDifference = player.calculateScoreDifference(dealer);
        if (scoreDifference > 0) {
            return WIN;
        }
        if (scoreDifference < 0) {
            return DEFEAT;
        }
        return DRAW;
    }
}
