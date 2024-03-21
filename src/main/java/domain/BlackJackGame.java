package domain;

import domain.cards.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

    private final Deck deck;

    public BlackJackGame() {
        this.deck = Deck.createShuffledDeck();
    }

    public void shareInitCards(Players players, Dealer dealer) {
        players.getPlayers().forEach(this::hitInitCards);
        hitInitCards(dealer);
    }

    private void hitInitCards(Player gamer) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            gamer.hit(deck.pickOneCard());
        }
    }

    public void playerHit(Player player) {
        player.hit(deck.pickOneCard());
    }
}
