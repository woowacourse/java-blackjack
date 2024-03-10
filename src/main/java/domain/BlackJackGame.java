package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public void prepareCards(final Gamers gamers) {
        for (Gamer gamer : gamers.getGamers()) {
            drawTwoCardsForGamer(gamer);
        }
    }

    private void drawTwoCardsForGamer(final Gamer gamer) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            gamer.hit(deck.draw());
        }
    }

    public boolean succeededGiving(final Gamer gamer) {
        if (!gamer.isStay()) {
            gamer.hit(deck.draw());
            return true;
        }
        return false;
    }

    public PlayerResults findPlayerResult(final Gamers gamers) {
        Dealer dealer = gamers.findDealer();
        Map<Player, Result> playerResults = new LinkedHashMap<>();
        for (Player player : gamers.findPlayers()) {
            Result playerResult = Result.getPlayerResultWith(player, dealer);
            playerResults.put(player, playerResult);
        }
        return new PlayerResults(playerResults);
    }
}
