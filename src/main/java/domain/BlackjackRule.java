package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackRule {
    private final Map<Player, Entry<Integer, Integer>> results = new LinkedHashMap<>();

    public BlackjackResult finishGame(final List<Player> players, final Player dealer) {
        results.put(dealer, Map.entry(0, 0));
        players.forEach(player -> {
            results.put(player, Map.entry(0, 0));
            judgeWinningHand(dealer, player);
        });
        return new BlackjackResult(results);
    }

    private void judgeWinningHand(final Player dealer, final Player player) {
        if (player.calculateScore() > 21) {
            calculate(dealer, player, 0, 1);
            return;
        }
        if (dealer.calculateScore() > 21) {
            calculate(dealer, player, 1, 0);
            return;
        }
        if (dealer.calculateScore() >= player.calculateScore()) {
            calculate(dealer, player, 0, 1);
        }
    }

    private void calculate(final Player dealer, final Player player, final int win, final int lose) {
        final Entry<Integer, Integer> playerEntry = results.get(player);
        final Entry<Integer, Integer> dealerEntry = results.get(dealer);

        results.put(player, Map.entry(playerEntry.getKey() + win, playerEntry.getValue() + lose));
        results.put(dealer, Map.entry(dealerEntry.getKey() + lose, dealerEntry.getValue() + win));
    }
}
