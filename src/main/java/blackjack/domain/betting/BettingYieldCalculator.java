package blackjack.domain.betting;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingYieldCalculator {
    public static final double PLAYER_YIELD_BLACKJACK = 1.5;
    public static final double PLAYER_YIELD_WIN = 1;
    public static final double PLAYER_YIELD_PUSH = 0;
    public static final double PLAYER_YIELD_LOSE = -1;
    private static final int MAX_BLACKJACK_SCORE = 21;

    private final Map<Player, Double> playersYields = new HashMap<>();

    public BettingYieldCalculator(Dealer dealer, List<Player> players) {
        if (dealer.isBlackJack()) {
            decideWhenDealerBlackjack(players);
            return;
        }
        decideWhenDealerNotBlackjack(dealer, players);
    }

    private void decideWhenDealerBlackjack(List<Player> players) {
        players.forEach(this::decideEachWhenDealerBlackjack);
    }

    private void decideEachWhenDealerBlackjack(Player player) {
        if (player.isBlackJack()) {
            saveBothDraw(player);
            return;
        }
        savePlayerLose(player);
    }

    private void decideWhenDealerNotBlackjack(Dealer dealer, List<Player> players) {
        players.forEach(player ->
                decideEachWhenDealerNotBlackjack(dealer, player));
    }

    private void decideEachWhenDealerNotBlackjack(Dealer dealer, Player player) {
        if (player.isBlackJack()) {
            savePlayerBlackjackWin(player);
            return;
        }

        if (isBust(player) || isBust(dealer)) {
            compareBust(dealer, player);
            return;
        }
        compareScore(dealer, player);
    }

    private boolean isBust(Participant participant) {
        return participant.getScore() > MAX_BLACKJACK_SCORE;
    }

    private void compareBust(Dealer dealer, Player player) {
        if (isBust(player)) {
            savePlayerLose(player);
            return;
        }
        if (isBust(dealer)) {
            savePlayerWin(player);
        }
    }

    private void compareScore(Dealer dealer, Player player) {
        if (player.getScore() == dealer.getScore()) {
            saveBothDraw(player);
            return;
        }
        if (player.getScore() > dealer.getScore()) {
            savePlayerWin(player);
            return;
        }
        savePlayerLose(player);
    }

    private void savePlayerBlackjackWin(Player player) {
        playersYields.put(player, PLAYER_YIELD_BLACKJACK);
    }

    private void savePlayerWin(Player player) {
        playersYields.put(player, PLAYER_YIELD_WIN);
    }

    private void saveBothDraw(Player player) {
        playersYields.put(player, PLAYER_YIELD_PUSH);
    }

    private void savePlayerLose(Player player) {
        playersYields.put(player, PLAYER_YIELD_LOSE);
    }

    public Map<Player, Double> getPlayersYields() {
        return Collections.unmodifiableMap(playersYields);
    }
}
