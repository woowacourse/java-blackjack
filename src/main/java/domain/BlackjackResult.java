package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackResult {

    Map<Player, Entry<Integer, Integer>> results = new LinkedHashMap<>();

    public BlackjackResultDTO finishGame(List<Player> players, Player dealer) {
        results.put(dealer, Map.entry(0, 0));
        for (var player : players) {
            if (isDealer(dealer, player)) {
                continue;
            }

            results.put(player, Map.entry(0, 0));

            if (player.calculateScore() > 21) {
                calculate(dealer, player, 0, 1);
                continue;
            }
            if (dealer.calculateScore() > 21) {
                calculate(dealer, player, 1, 0);
                continue;
            }
            if (dealer.calculateScore() >= player.calculateScore()) {
                calculate(dealer, player, 0, 1);
            }
        }
        return new BlackjackResultDTO(results);
    }

    private boolean isDealer(final Player dealer, final Player player) {
        return player.getName().equals(dealer.getName());
    }

    private void calculate(final Player dealer, final Player player, final int win, final int lose) {
        Entry<Integer, Integer> playerEntry = results.get(player);
        Entry<Integer, Integer> dealerEntry = results.get(dealer);

        results.put(player, Map.entry(playerEntry.getKey() + win, playerEntry.getValue() + lose));
        results.put(dealer, Map.entry(dealerEntry.getKey() + lose, dealerEntry.getValue() + win));
    }
}
