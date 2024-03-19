package domain;

import domain.cards.Card;
import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        this.deck = Deck.createShuffledDeck();
    }

    public void shareInitCards() {
        players.getPlayers().forEach(this::hitInitCards);
        hitInitCards(dealer);
    }

    private void hitInitCards(Player gamer) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            gamer.hit(deck.pickOneCard());
        }
    }

    public void allowHit(Player player) {
        player.hit(deck.pickOneCard());
    }

    public Card dealerHit() {
        Card pickedCard = deck.pickOneCard();
        dealer.hit(pickedCard);
        return pickedCard;
    }
}
