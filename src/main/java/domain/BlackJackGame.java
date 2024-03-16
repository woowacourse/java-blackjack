package domain;

import domain.cards.Card;
import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

    Gamers gamers;
    Deck deck;

    public BlackJackGame(Players players) {
        this.gamers = new Gamers(players, new Dealer());
        this.deck = Deck.createShuffledDeck();
    }

    public void shareInitCards() {
        List<Player> allGamers = gamers.allGamers();
        allGamers.forEach(this::hitInitCards);
    }

    private void hitInitCards(Player gamer) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            gamer.hit(deck.pickOneCard());
        }
    }

    public boolean canPlayerHit(Player player) {
        return player.canHit();
    }

    public boolean canDealerMoreHit() {
        return gamers.dealer().canHit();
    }

    public void allowHit(Player player) {
        player.hit(deck.pickOneCard());
    }

    public Card dealerHit() {
        Card pickedCard = deck.pickOneCard();
        dealer().hit(pickedCard);
        return pickedCard;
    }

    public Dealer dealer() {
        return gamers.dealer();
    }

    public List<Player> players() {
        return gamers.players();
    }
}
