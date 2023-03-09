package domain;

import domain.deck.CardDeck;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

public class BlackjackGame {
    private static final int INITIAL_CARD_SET = 2;

    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackGame(Players players, CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void distributeInitialCard() {
        for (int i = 0; i < INITIAL_CARD_SET; i++) {
            distributeDealer();
            distributePlayers();
        }
    }

    public void distributeDealer() {
        Dealer dealer = players.findDealer();
        dealer.addCard(cardDeck.poll());
    }

    public void distributePlayers() {
        for (Player player : players.getPlayersWithOutDealer()) {
            distributePlayer(player);
        }
    }

    private void distributePlayer(Player player) {
        player.addCard(cardDeck.poll());
    }

    public void distributeByCommand(Player player, boolean command) {
        if (player.isCommandYes(command)) {
            distributePlayer(player);
        }
    }
}
