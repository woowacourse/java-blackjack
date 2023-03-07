package domain;

import domain.deck.CardDeck;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final int BLACK_JACK = 21;
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

    public List<Result> getDealerResult(Players players) {
        List<Result> dealerResults = new ArrayList<>();
        for (Player player : players.getPlayersWithOutDealer()) {
            Result result = getPlayersResult(player);
            dealerResults.add(result.getReverseResult());
        }
        return dealerResults;
    }

    public Result getPlayersResult(Player player) {
        return isPlayerWin(players.findDealer().getCardsSum(), player.getCardsSum());
    }

    public Result isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > BLACK_JACK || (dealerSum > playerSum && !(dealerSum > BLACK_JACK))) {
            return Result.LOSE;
        }
        if (dealerSum > BLACK_JACK || dealerSum < playerSum) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
