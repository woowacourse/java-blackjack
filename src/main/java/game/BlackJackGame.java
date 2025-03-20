package game;

import card.CardDeck;
import participant.Dealer;
import participant.Player;
import participant.Players;

public class BlackJackGame {

    private static final int INITIAL_DEAL_COUNT = 2;
    private static final int ADDITIONAL_CARD_DEFAULT_COUNT = 1;

    private final Players players;
    private final Dealer dealer;
    private final CardDeck deck;

    public BlackJackGame(Players players, Dealer dealer, CardDeck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void dealInitialCards() {
        dealCard(dealer, INITIAL_DEAL_COUNT);
        for (Player player : players.getPlayers()) {
            dealCard(player, INITIAL_DEAL_COUNT);
        }
    }

    public void dealCard(Player player, int amount) {
        for (int i = 0; i < amount; i++) {
            player.addCard(deck.pickCard());
        }
    }

    public void dealCard(Dealer dealer, int amount) {
        for (int i = 0; i < amount; i++) {
            dealer.addCard(deck.pickCard());
        }
    }

    public void receiveAdditionalCard(Player player) {
        dealCard(player, ADDITIONAL_CARD_DEFAULT_COUNT);
    }

    public void receiveAdditionalCard(Dealer dealer) {
        dealCard(dealer, ADDITIONAL_CARD_DEFAULT_COUNT);
    }
}
