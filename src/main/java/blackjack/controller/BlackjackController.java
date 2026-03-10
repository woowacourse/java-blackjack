package blackjack.controller;

import blackjack.domain.*;
import blackjack.service.CardDistributor;
import blackjack.service.Game;
import blackjack.utils.InputParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
    private final Game game;

    public BlackjackController(CardDistributor cardDistributor, Referee referee) {
        this.game = new Game(cardDistributor, referee);
    }

    public void startGame() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = getPlayers(playerNames);
        Dealer dealer = new Dealer();

        setupInitialHand(players, dealer, playerNames);
        processPlayersTurn(players);
        playDealerTurn(dealer);

        calculateFinalScore(players, dealer);
        calculateFinalGameResult(players, dealer);
    }

    private static List<String> getPlayerNames() {
        String playerNamesStr = InputView.askPlayerNames();
        return InputParser.splitPlayerNames(playerNamesStr);
    }

    private List<Player> getPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private void setupInitialHand(List<Player> players, Dealer dealer, List<String> playerNames) {
        game.distributeInitialCards(players, dealer);
        OutputView.printInitialCardsDistribution(playerNames);
        printInitialCards(players, dealer);
    }

    private static void printInitialCards(List<Player> players, Dealer dealer) {
        Map<String, List<String>> playerCards = new HashMap<>();
        for (Player player : players) {
            playerCards.put(player.getName(), player.getCardNames());
        }
        OutputView.printAllUserCards(playerCards, dealer.getCardNames());
    }


    private void processPlayersTurn(List<Player> players) {
        for (Player player : players) {
            drawUntilPlayerStand(player);
        }
    }

    private void drawUntilPlayerStand(Player player) {
        while (!player.isBust() && isHit(player)) {
            game.drawCardToPlayer(player);
            OutputView.printDrawnCards(player.getName(), player.getCardNames());
        }
    }

    private void playDealerTurn(Dealer dealer) {
        game.dealerDrawsCardsUntilDone(dealer);
        OutputView.printDealerCardDrawnResult(dealer.getAdditionalDrawnCardCount());
    }

    private boolean isHit(Player player) {
        String hitOrStand = InputView.askHitOrStand(player.getName());
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }

    private void calculateFinalScore(List<Player> players, Dealer dealer) {
        Map<String, List<String>> playerCards = new HashMap<>();
        Map<String, Integer> playerScores = new HashMap<>();
        for (Player player : players) {
            playerCards.put(player.getName(), player.getCardNames());
            playerScores.put(player.getName(), player.calculateTotalScore());
        }

        List<String> dealerCards = dealer.getCardNames();
        int dealerScore = dealer.calculateTotalScore();

        OutputView.printFinalCardScores(playerCards, dealerCards, playerScores, dealerScore);
    }

    private void calculateFinalGameResult(List<Player> players, Dealer dealer) {
        GameResult gameResult = game.judgeTotalGameResult(players, dealer);
        Map<ScoreCompareResult, Integer> dealerResult = gameResult.dealerResult();
        Map<Player, ScoreCompareResult> playerResult = gameResult.playerResults();
        HashMap<String, ScoreCompareResult> playerNameResult = new HashMap<>();

        for (Entry<Player, ScoreCompareResult> entry : playerResult.entrySet()) {
            playerNameResult.put(entry.getKey().getName(), entry.getValue());
        }
        OutputView.printFinalResult(dealerResult, playerNameResult);
    }

}
