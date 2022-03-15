package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<String, Integer> earnings;

    public GameResult(Map<String, Integer> earnings) {
        this.earnings = Collections.unmodifiableMap(new LinkedHashMap<>(earnings));
    }

    public static GameResult of(Dealer dealer, List<Player> players) {
        Map<String, Integer> playersEarnings = new LinkedHashMap<>();

        for (Player player : players) {
            Outcome playerOutcome = Outcome.judge(player, dealer);
            int playerEarnings = calculateEarnings(player, playerOutcome);
            int dealerEarnings = getDealerEarnings(playerEarnings);
            playersEarnings.merge(dealer.getName(), dealerEarnings, Integer::sum);
            playersEarnings.put(player.getName(), playerEarnings);
        }

        return new GameResult(playersEarnings);
    }

    private static int getDealerEarnings(int playerEarnings) {
        return playerEarnings * (-1);
    }

    private static int calculateEarnings(Player player, Outcome outcome) {
        return (int) (player.getBetting().getValue() * outcome.getEarningsRate());
    }

    public Map<String, Integer> getEarnings() {
        return earnings;
    }
}
