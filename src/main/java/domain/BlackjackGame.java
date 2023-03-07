package domain;

import java.util.*;

public class BlackjackGame {
    public final static int BLACK_JACK = 21;
    private final static int INITIAL_CARD_SET = 2;
    private final Dealer dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackGame(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void shuffleCardDeck() {
        cardDeck.shuffle();
    }

    public void distributeInitialCard() {
        for (int i = 0; i < INITIAL_CARD_SET; i++) {
            distributeDealer();
            distributePlayers();
        }
    }

    public void distributeDealer() {
        dealer.addCard(cardDeck.poll());
    }

    public void distributePlayers() {
        for (Player player : players.getPlayers()) {
            distributePlayer(player);
        }
    }

    public void distributePlayer(Player player) {
        player.addCard(cardDeck.poll());
    }

    public void selectByPlayer(Player player, Command command) {
        if (command.equals(Command.YES)) {
            player.addCard(cardDeck.poll());
        }
    }

    public GameResult getGameResult() {
        return new GameResult(dealer, players);
    }

}
