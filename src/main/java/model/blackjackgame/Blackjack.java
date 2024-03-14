package model.blackjackgame;

import model.dealer.Dealer;
import model.player.Player;

public class Blackjack {

    private final boolean dealer;
    private final boolean player;

    public Blackjack(Dealer dealer, Player player) {
        this.dealer = dealerIsBlackjack(dealer);
        this.player = false;
    }

    public boolean dealerIsBlackjack(Dealer dealer) {
        return dealer.isBlackjack();
    }
}
