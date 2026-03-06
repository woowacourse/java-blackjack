package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;

public class BlackJackService {

    private final static int INIT_HAND_VALUE = 2;

    private Deck deck;
    private Dealer dealer;
    private Players players;

    public BlackJackService(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initHand() {
        for (int i = 0; i < INIT_HAND_VALUE; i++) {
            dealer.hit(deck.drawCard());
        }

        for (Player player : players.getPlayers()) {
            for (int i = 0; i < INIT_HAND_VALUE; i++) {
                player.hit(deck.drawCard());
            }
        }
    }
}
