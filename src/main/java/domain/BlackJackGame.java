package domain;

import domain.card.Card;
import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public void prepareCards(final Dealer dealer, final Players players) {
        dealer.receive(drawTwoCards());
        for (final Player player : players.getPlayers()) {
            player.receive(drawTwoCards());
        }
    }

    private List<Card> drawTwoCards() {
        return Stream.generate(deck::draw)
                .limit(INITIAL_CARD_COUNT)
                .toList();
    }

    public boolean takeTurn(final Gamer gamer) {
        if (!gamer.shouldStay()) {
            gamer.hit(deck.draw());
            return true;
        }
        return false;
    }

    public PlayerResults findPlayerResult(final Dealer dealer, Players players) {
        Map<Player, Result> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Result playerResult = isPlayerWin(dealer, player);
            playerResults.put(player, playerResult);
        }
        return new PlayerResults(playerResults);
    }

    private Result isPlayerWin(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                return Result.TIE;
            }
            return Result.WIN;
        }
        if (dealer.isBlackJack()) {
            return Result.LOSE;
        }
        if (player.calculateTotalScore() > dealer.calculateTotalScore()) {
            return Result.WIN;
        }
        if (player.calculateTotalScore() == dealer.calculateTotalScore()) {
            return Result.TIE;
        }
        return Result.LOSE;
    }
}
