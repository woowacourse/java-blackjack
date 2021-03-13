package blackjack.domain.card;

import blackjack.controller.BlackJackController;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class GameTable {
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
        if (doesPlayerWantMoreCard.equals(BlackJackController.YES)) {
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
}
