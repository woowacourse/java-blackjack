package blackjack.controller;

import blackjack.domain.*;
import blackjack.service.CardDistributor;
import blackjack.service.Game;
import blackjack.utils.InputParser;

import java.util.*;
import java.util.stream.Collectors;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
    private final Game game;

    public BlackjackController(CardDistributor cardDistributor) {
        this.game = new Game(cardDistributor);
    }

    public void startGame() {
        List<String> playerNames = getPlayerNames();
        List<Participant> players = createPlayers(playerNames);
        Participant dealer = Participant.createDealer();

        setupInitialHand(players, dealer, playerNames);
        processPlayersTurn(players);
        playDealerTurn(dealer);

        calculateFinalScore(players, dealer);
        calculateFinalGameProfit(players, dealer);
    }

    private List<String> getPlayerNames() {
        String names = InputView.askPlayerNames();
        return InputParser.splitPlayerNames(names);
    }

    private List<Participant> createPlayers(List<String> playerNames) {
        List<Participant> players = new ArrayList<>();
        for (String playerName : playerNames) {
            String amountStr = InputView.askPlayerBettingAmount(playerName);
            long amount = InputParser.convertNumber(amountStr);
            players.add(Participant.createPlayer(playerName, Money.fromBettingAmount(amount)));
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
        if (additionalCount > 0) {
            OutputView.printDealerCardDrawnResult(additionalCount);
        }
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
        List<ParticipantResult> playerResults = players.stream()
                .map(player -> new ParticipantResult(player.getName(), player.getCardNames(), player.calculateTotalScore()))
                .collect(Collectors.toList());

        ParticipantResult dealerResult = new ParticipantResult(dealer.getName(), dealer.getCardNames(), dealer.calculateTotalScore());
        OutputView.printFinalCardScores(playerResults, dealerResult);
    }

    private void calculateFinalGameProfit(List<Participant> players, Participant dealer) {
        Map<String, Long> playersProfit = new LinkedHashMap<>();
        long totalPlayersProfit = 0;
        for (Participant participant : players) {
            Player player = (Player) participant;
            Money profit = player.calculateFinalProfit(dealer);

            long bettingMoney = profit.getBettingMoney();
            playersProfit.put(player.getName(), bettingMoney);
            totalPlayersProfit += bettingMoney;
        }
        long dealerProfit = -totalPlayersProfit;
        OutputView.printFinalProfit(dealerProfit, playersProfit);
    }
}
