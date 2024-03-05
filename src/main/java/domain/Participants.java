package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Player dealer;
    private final List<Player> players;
    private final Deck deck;

    public Participants(Player dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
        Arrays.stream(Rank.values())
                .forEach((rank) -> {
                    Arrays.stream(Shape.values())
                            .forEach((shape) -> deck.addCard(new Card(shape, rank)));
                });
    }

    public void initialSetting() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.receiveCard(this.deck.pickRandomCard());
            players.forEach(player -> player.receiveCard(this.deck.pickRandomCard()));
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void addCard(Player player) {
        player.receiveCard(deck.pickRandomCard()); // TODO refactor

    }

    public Map<Player, Boolean> calculateVictory() {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> {
            if (player.getDeck().calculateTotalScore() > 21) {
                result.put(player, false);
                return;
            }
            if (dealer.getDeck().calculateTotalScore() > 21) {
                result.put(player, true);
                return;
            }
            if (dealer.getDeck().calculateTotalScore() >= player.getDeck().calculateTotalScore()) {
                result.put(player, false);
                return;
            }
            result.put(player, true);
        });

        return result;
    }
}
