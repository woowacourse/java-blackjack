package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;

public class GameTable {
    public static final String PLAYER_WANT_MORE_CARD = "Y";

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameTable(Players players) {
        this.deck = new Deck(Card.values());
        this.dealer = new Dealer();
        this.players = players;
    }

    public void drawAtFirst() {
        dealer.drawAtFirst(deck);
        players.drawAtFirst(deck);
    }

    public void draw(Player player, String doesPlayerWantMoreCard) {
        if (doesPlayerWantMoreCard.equals(PLAYER_WANT_MORE_CARD)) {
            player.hit(deck.pop());
        }
    }

    public void drawMoreCardToDealer() {
        dealer.hit(deck.pop());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }

    public List<Player> getEveryPlayer() {
        return this.players.getPlayers();
    }
}
