package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackResult {

    Map<Player, Entry<Integer, Integer>> results = new LinkedHashMap<>();

    public BlackjackResultDTO finishGame(Players players, Player dealer) {
        results.put(dealer, Map.entry(0, 0));
        for (var player : players.getPlayers()) {
            determineWinner(dealer, player);
        }
        return new BlackjackResultDTO(results);
    }

    private void determineWinner(final Player dealer, final Player player) {
        if (isDealer(dealer, player)) {
            return;
        }

        results.put(player, Map.entry(0, 0));

        if (player.calculateScore() > 21 || dealer.calculateScore() >= player.calculateScore()) {
            calculate(dealer, player, 1, 0);
            return;
        }
        if (dealer.calculateScore() > 21) {
            calculate(dealer, player, 0, 1);
            return;
        }

        calculate(dealer, player, 0, 1);
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
