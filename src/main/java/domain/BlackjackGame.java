package domain;

import domain.deck.CardDeck;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    public static final int BLACK_JACK = 21;
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

    public void distributeByCommand(Player player, String command) {
        if (player.isCommandYes(command)) {
            distributePlayer(player);
        }
    }

    public Map<String, List<Result>> getDealerResult() {
        Map<String, List<Result>> dealerResult = new LinkedHashMap<>();
        List<Result> dealerResults = new ArrayList<>();
        for (String name : getPlayersResult().keySet()) {
            Result result = getPlayersResult().get(name);
            dealerResults.add(result.getReverseResult());
        }
        dealerResult.put(players.findDealer().getName(), dealerResults);
        return dealerResult;
    }

    public Map<String, Result> getPlayersResult() {
        Map<String, Result> result = new LinkedHashMap<>();
        int dealerSum = players.findDealer().getCardsSum();
        for (Player player : players.getPlayersWithOutDealer()) {
            result.put(player.getName(), isPlayerWin(dealerSum, player.getCardsSum()));
        }
        return result;
    }

    public Result isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > BLACK_JACK) {
            return Result.LOSE;
        }
        if (dealerSum > BLACK_JACK) {
            return Result.WIN;
        }
        if (dealerSum > playerSum) {
            return Result.LOSE;
        }
        if (dealerSum < playerSum) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
