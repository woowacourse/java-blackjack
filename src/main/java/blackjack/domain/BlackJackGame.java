package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackJackGame {

    private static final int STARTING_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(String playersInput, List<Card> deck) {
        this.players = Players.valueOf(playersInput);
        this.deck = new Deck(deck);
        this.dealer = new Dealer();
    }

    public void prepare() {
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            players.unwrap().stream().forEach(player -> player.drawCard(deck));
            dealer.drawCard(deck);
        }
    }

    public List<Card> getDeck() {
        return this.deck.getCards();
    }

    public List<Player> getPlayers() {
        return this.players.unwrap();
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
