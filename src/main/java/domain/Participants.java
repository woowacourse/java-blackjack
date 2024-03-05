package domain;

import java.util.Arrays;
import java.util.List;

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
}
