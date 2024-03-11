package domain;

import domain.cards.Card;
import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void shareInitCards(Deck deck) {
        List<Player> players = new ArrayList<>(this.players.getPlayers());
        players.add(dealer);
        for (Player player : players) {
            hitInitCards(player, deck);
        }
    }

    private void hitInitCards(Player player, Deck deck) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            player.hit(deck.pickOneCard());
        }
    }

    public boolean canPlayerMoreHit(Player player, HitOption hitOption) {
        return player.isNotBust() && hitOption.doHit();
    }

    public Card allowHit(Player player, Deck deck) {
        Card pickedCard = deck.pickOneCard();
        player.hit(pickedCard);
        return pickedCard;
    }

    public boolean canDealerMoreHit(Dealer dealer) {
        return dealer.canHit();
    }
}
