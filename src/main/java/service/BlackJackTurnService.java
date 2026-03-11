package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.card.Card;

public class BlackJackTurnService {

    public void playerHit(Player player, Deck deck) {
        Card card = deck.drawCard();
        player.draw(card);
    }

    public void dealerHit(Dealer dealer, Deck deck) {
        Card card = deck.drawCard();
        dealer.draw(card);
    }

    public boolean canDealerHit(Dealer dealer) {
        return dealer.getScore() < 17;
    }

    public boolean canPlayerHit(Player player, String input) {
        return isPlayerUnder21(player) && input.equals("y");
    }

    public boolean isPlayerUnder21(Player player) {
        return player.getScore() < 21;
    }
}
