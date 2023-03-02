package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(String inputNames) {
        this.dealer = new Dealer();
        this.players = new Players(inputNames);
    }

    public void handOutInitCards() {
        List<Integer> keys = shuffleDeck();
        handOutInitCardsTo(keys, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(keys, player));
    }

    private List<Integer> shuffleDeck() {
        List<Integer> keys = Deck.getKeys();
        Collections.shuffle(keys);
        return keys;
    }

    private void handOutInitCardsTo(final List<Integer> keys, final Participant participant) {
        for (int i = 0; i < 2; i++) {
            Card card = Deck.from(keys.remove(0));
            participant.receiveCard(card);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
