package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final List<Player> winners = new ArrayList<>();
    private final List<Player> losers = new ArrayList<>();

    private final Map<Player, Grade> result = new HashMap<>();

    public boolean isKeepPlaying(final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            result.put(player, Grade.grade(dealer, player));
        }
        return result.values().stream()
                .anyMatch(grade -> grade.equals(Grade.PROCEED));
    }

    public void compete(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            losers.add(player);
            return;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            winners.add(player);
            return;
        }
        losers.add(player);
    }

    public int numberOfWinners() {
        return winners.size();
    }

    public int numberOfLosers() {
        return losers.size();
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
