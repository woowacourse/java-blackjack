package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Blackjack {

    private final Players players;
    private final Player dealer;
    private final Deck deck;

    public Blackjack(final Players players, final Player dealer, final Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void dealCardsToPlayer(final Player player) {
        player.addCard(deck.poll());
    }

    public void initializePlayers() {
        players.getPlayers().forEach(this::initializePlayer);
    }

    public void initializeDealer() {
        dealer.addCard(deck.poll());
        dealer.addCard(deck.poll());
    }

    // "에포케"

    // player > 21, dealer > 21 <- 패배
    // player = 21, dealer = 21 <- 승
    // player <= 21 dealer <= 21
    // player > dealer  -> player sungri
    // player
    public Map<Player, Entry<Integer, Integer>> finishGame() {
        Map<Player, Entry<Integer, Integer>> results = new HashMap<>();

        results.put(dealer, Map.entry(0, 0));
        for (var player : players.getPlayers()) {
            Entry<Integer, Integer> dealerEntry = results.get(dealer);
            if (player.getName().equals(dealer.getName())) {
                continue;
            }
            results.put(player, Map.entry(0, 0));
            Entry<Integer, Integer> playerEntry = results.get(player);

            if (player.calculateScore() > 21) {
                results.put(dealer, Map.entry(dealerEntry.getKey() + 1, dealerEntry.getValue()));
                results.put(player, Map.entry(playerEntry.getKey(), playerEntry.getValue() + 1));
            } else if (dealer.calculateScore() > 21) {
                results.put(dealer, Map.entry(dealerEntry.getKey(), dealerEntry.getValue() + 1));
                results.put(player, Map.entry(playerEntry.getKey() + 1, playerEntry.getValue()));
            } else {
                if (dealer.calculateScore() >= player.calculateScore()) {
                    results.put(dealer, Map.entry(dealerEntry.getKey() + 1, dealerEntry.getValue()));
                    results.put(player, Map.entry(playerEntry.getKey(), playerEntry.getValue() + 1));
                } else {
                    results.put(dealer, Map.entry(dealerEntry.getKey(), dealerEntry.getValue() + 1));
                    results.put(player, Map.entry(playerEntry.getKey() + 1, playerEntry.getValue()));
                }
            }

        }
        return results;
    }

    private void initializePlayer(final Player player) {
        player.addCard(deck.poll());
        player.addCard(deck.poll());
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }
}
