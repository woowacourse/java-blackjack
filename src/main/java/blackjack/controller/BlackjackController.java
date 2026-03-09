package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.ScoreCompareResult;
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
    private final CardDistributor cardDistributor;
    private final Game game;

    public BlackjackController(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
        this.game = new Game(cardDistributor);
    }

    public void startGame() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = getPlayers(playerNames);
        Dealer dealer = new Dealer();

        setupInitialHand(players, dealer, playerNames); // 2장씩 카드 분배
        
        processTurn(players); // 플레이어 hit or stand
        playDealerTurn(dealer);

        calculateFinalScore(players, dealer);
        calculateFinalGameResult(players, dealer);
    }

    private void setupInitialHand(List<Player> players, Dealer dealer, List<String> playerNames) {
        distributeInitialCards(players, dealer);
        OutputView.printInitialCardsDistribution(playerNames);
        printInitialCards(players, dealer);
    }

    private static List<String> getPlayerNames() {
        String playerNamesStr = InputView.askPlayerNames();
        List<String> playerNames = InputParser.splitPlayerNames(playerNamesStr);
        return playerNames;
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

    private void playDealerTurn(Dealer dealer) {
        game.dealerDrawsCardsUntilDone(dealer);
        OutputView.printDealerCardDrawnResult(dealer.getAdditionalDrawnCardCount());
    }

    private void processTurn(List<Player> players) {
        for (Player player : players) {
            while (true) {
                String hitOrStand = InputView.askHitOrStand(player.getName());
                if (!isHit(hitOrStand)) {
                    break;
                }
                cardDistributor.distributeCardToPlayer(player);
                OutputView.printDrawnCards(player.getName(), player.getCardNames());
                if (player.isBust()) {
                    break;
                }
            }
        }
    }

    private static void printInitialCards(List<Player> players, Dealer dealer) {
        Map<String, List<String>> playerCards = new HashMap<>();
        for (Player player : players) {
            playerCards.put(player.getName(), player.getCardNames());
        }
        OutputView.printAllUserCards(playerCards, dealer.getCardNames());
    }

    private List<Player> getPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
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

    private void distributeInitialCards(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            cardDistributor.distributeTwoCardsToPlayer(player);
        }
        cardDistributor.distributeTwoCardsToDealer(dealer);
    }

    private boolean isHit(String hitOrStand) {
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }

}
