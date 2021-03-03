package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    public static final int STARTING_CARD_COUNT = 2;

    private final Players unpreparedPlayers;
    private final Players preparedPlayers;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(String playersInput, List<Card> deck) {
        this.unpreparedPlayers = Players.valueOf(playersInput);
        this.preparedPlayers = new Players(new ArrayList<>());
        this.deck = new Deck(deck);
        this.dealer = new Dealer();
    }

    public void prepare() {
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            unpreparedPlayers.unwrap().stream().forEach(player -> player.drawCard(deck));
            dealer.drawCard(deck);
        }
    }

    public Player nextPlayer() {
        Player player = unpreparedPlayers.pop();
        preparedPlayers.push(player);
        return player;
    }

    public boolean isPrepared() {
        return unpreparedPlayers.isEmpty();
    }

    public List<Card> getDeck() {
        return this.deck.getCards();
    }

    public List<Player> getPlayers() {
        return this.unpreparedPlayers.unwrap();
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
