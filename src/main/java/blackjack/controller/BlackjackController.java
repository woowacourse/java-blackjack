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

        List<Participant> players = getPlayers(playerNames);
        Participant dealer = Participant.createDealer();

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

    private List<Participant> getPlayers(List<String> playerNames) {
        List<Participant> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(Participant.createPlayer(playerName));
        }
        return players;
    }

    private void setupInitialHand(List<Participant> players, Participant dealer, List<String> playerNames) {
        game.distributeInitialCards(players, dealer);
        OutputView.printInitialCardsDistribution(playerNames);
        printInitialCards(players, dealer);
    }

    private static void printInitialCards(List<Participant> players, Participant dealer) {
        Map<String, List<String>> playerCards = new HashMap<>();
        for (Participant player : players) {
            playerCards.put(player.getName(), player.getCardNames());
        }
        OutputView.printAllUserCards(playerCards, dealer.getCardNames());
    }


    private void processPlayersTurn(List<Participant> players) {
        for (Participant player : players) {
            drawUntilPlayerStand(player);
        }
    }

    private void drawUntilPlayerStand(Participant player) {
        while (!player.isBust() && isHit(player)) {
            game.drawCardToPlayer(player);
            OutputView.printDrawnCards(player.getName(), player.getCardNames());
        }
    }

    private void playDealerTurn(Participant dealer) {
        int additionalCount = game.dealerDrawsCardsUntilDone(dealer);
        OutputView.printDealerCardDrawnResult(additionalCount);
    }

    private boolean isHit(Participant player) {
        String hitOrStand = InputView.askHitOrStand(player.getName());
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }

    private void calculateFinalScore(List<Participant> players, Participant dealer) {
        List<ParticipantResult> playerResults = new ArrayList<>();
        for (Participant player : players) {
            ParticipantResult participantResult = new ParticipantResult(player.getName(), player.getCardNames(), player.calculateTotalScore());
            playerResults.add(participantResult);
        }

        ParticipantResult dealerResult = new ParticipantResult(dealer.getName(), dealer.getCardNames(), dealer.calculateTotalScore());
        OutputView.printFinalCardScores(playerResults, dealerResult);
    }

    private void calculateFinalGameResult(List<Participant> players, Participant dealer) {
        GameResult gameResult = game.judgeTotalGameResult(players, dealer);
        Map<ScoreCompareResult, Integer> dealerResult = gameResult.dealerResult();
        Map<Participant, ScoreCompareResult> playerResult = gameResult.playerResults();
        HashMap<String, ScoreCompareResult> playerNameResult = new HashMap<>();

        for (Entry<Participant, ScoreCompareResult> entry : playerResult.entrySet()) {
            playerNameResult.put(entry.getKey().getName(), entry.getValue());
        }
        OutputView.printFinalResult(dealerResult, playerNameResult);
    }

}
