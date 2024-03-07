package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Blackjack {

    private final Players players;
    private final Player dealer;
    private final Deck deck;

    // TODO : 필드 수 줄이기
    public Blackjack(final Players players) {
        this.players = players;
        this.dealer = new Player("딜러");
        this.deck = new Deck();
        dealCardsToPlayers();
        dealCardsToDealer();
    }

    public void dealCard(final Player player) {
        player.addCard(deck.draw());
    }

    private void dealCardsToPlayers() {
        players.getPlayers().forEach(this::initializePlayer);
    }

    private void dealCardsToDealer() {
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    // "에포케"

    // player > 21, dealer > 21 <- 패배
    // player = 21, dealer = 21 <- 승
    // player <= 21 dealer <= 21
    // player > dealer  -> player sungri
    // player
    // TODO : 로직 개선
    public Map<Player, Entry<Integer, Integer>> finishGame() {
        Map<Player, Entry<Integer, Integer>> results = new LinkedHashMap<>();

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
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }
}
