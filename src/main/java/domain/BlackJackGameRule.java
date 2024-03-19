package domain;

import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

public class BlackJackGameRule {

    private static final int INIT_CARD_COUNT = 2;

    private final Deck deck;

    public BlackJackGameRule() {
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

    public void gamerHit(Player player) {
        player.hit(deck.pickOneCard());
    }
}
