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

        for (var player : players.getPlayers()) {
            results.put(dealer, Map.entry(0, 0));
            results.put(player, Map.entry(0, 0));
            if (player.calculateScore() > 21 && dealer.calculateScore() > 21) {
                results.put(player, Map.entry(results.get(player).getKey(), results.get(player).getValue() + 1));
                results.put(dealer, Map.entry(results.get(dealer).getKey(), results.get(dealer).getValue() + 1));
            } else if (player.calculateScore() > 21) {
                results.put(player, Map.entry(results.get(player).getKey(), results.get(player).getValue() + 1));
                results.put(dealer, Map.entry(results.get(dealer).getKey() + 1, results.get(dealer).getValue()));
            } else if (dealer.calculateScore() > 21) {
                results.put(player, Map.entry(results.get(player).getKey() + 1, results.get(player).getValue()));
                results.put(dealer, Map.entry(results.get(dealer).getKey(), results.get(dealer).getValue() + 1));
            } else if (player.calculateScore() == 21 && dealer.calculateScore() == 21) {
                results.put(player, Map.entry(results.get(player).getKey() + 1, results.get(player).getValue()));
                results.put(dealer, Map.entry(results.get(dealer).getKey() + 1, results.get(dealer).getValue()));
            } else {
                if (player.calculateScore() <= 21 && player.calculateScore() < dealer.calculateScore()) {
                    results.put(player, Map.entry(results.get(player).getKey() + 1, results.get(player).getValue()));
                    results.put(dealer, Map.entry(results.get(dealer).getKey(), results.get(dealer).getValue() + 1));
                } else if (dealer.calculateScore() <= 21 && dealer.calculateScore() < player.calculateScore()) {
                    results.put(player, Map.entry(results.get(player).getKey(), results.get(player).getValue() + 1));
                    results.put(dealer, Map.entry(results.get(dealer).getKey() + 1, results.get(dealer).getValue()));
                }
            }

//            if (player.calculateScore() <= 21 && player.calculateScore() > dealer.calculateScore()) {
//                results.put(player, Map.entry(0, 0));
//            } else {

//                results.put(player, 0);
//                Integer dealerScore = results.get(dealer);
//                results.put(dealer, dealerScore + 1);
//            }
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
