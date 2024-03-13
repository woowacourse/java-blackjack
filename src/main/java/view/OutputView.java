package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Map;
import model.card.Card;
import model.card.Cards;
import model.game.BlackjackGame;
import model.game.CardsScore;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayerResults;
import model.result.ResultStatus;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DEALER_NAME = "딜러";
    private static final String CARDS_DELIMITER = ", ";
    private static final String GAME_RESULT_DELIMITER = " ";
    private static final String INIT_CARDS_INTRO = NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String CARDS_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String FINAL_SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 승패";
    private static final String GAMER_RESULT_FORMAT = NEWLINE + "%s: %s";
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printInitialCards(BlackjackGame blackjackGame, Players players) {
        Dealer dealer = blackjackGame.getDealer();
        printInitCardsIntro(dealer, players);
        printDealerFirstCard(dealer);
        printAllPlayerCards(players);
    }

    private static void printInitCardsIntro(Dealer dealer, Players players) {
        String playerNames = String.join(CARDS_DELIMITER, players.names());
        System.out.printf(INIT_CARDS_INTRO, dealer.getName(), playerNames);
    }

    private static void printDealerFirstCard(Dealer dealer) {
        String name = dealer.getName();
        Card firstCard = dealer.getFirstCard();
        System.out.printf(CARDS_FORMAT, name, firstCard);
    }

    private static void printAllPlayerCards(Players players) {
        players.getPlayers()
            .forEach(OutputView::printPlayerCard);
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        String cardsMessage = createCardsMessage(player.getCards());
        System.out.printf(CARDS_FORMAT, player.getName(), cardsMessage);
    }

    private static String createCardsMessage(Cards cards) {
        return cards.getCards()
            .stream()
            .map(Card::toString)
            .collect(collectingAndThen(toList(), result -> String.join(CARDS_DELIMITER, result)));
    }

    public static void printAfterDealerHit(Dealer dealer) {
        System.out.printf(DEALER_HIT, dealer.getName());
    }

    public static void printGameResult(GameResult gameResult, DealerResult dealerResult, PlayerResults playerResults) {
        printFinalScore(gameResult);
        System.out.print(GAME_RESULT_INTRO);
        printDealerGameResult(dealerResult);
        printAllPlayerGameResults(playerResults);
    }

    private static void printFinalScore(GameResult gameResult) {
        CardsScore dealerScore = gameResult.getDealerScore();
        Map<String, CardsScore> playerScores = gameResult.getPlayerScores();
        printDealerFinalScore(dealerScore);
        printAllPlayerFinalScores(playerScores);
    }

    private static void printDealerFinalScore(CardsScore dealerScore) {
        String cardsMessage = createCardsMessage(dealerScore.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, DEALER_NAME, cardsMessage, dealerScore.getScore());
    }

    private static void printAllPlayerFinalScores(Map<String, CardsScore> playerScores) {
        playerScores.keySet()
            .forEach(playerName -> printPlayerFinalScore(playerName, playerScores));
    }

    private static void printPlayerFinalScore(String playerName, Map<String, CardsScore> playerScores) {
        CardsScore playerScore = playerScores.get(playerName);
        String cardsMessage = createCardsMessage(playerScore.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, playerName, cardsMessage, playerScore.getScore());
    }

    private static void printDealerGameResult(DealerResult dealerResult) {
        String result = dealerResult.allStatus()
            .stream()
            .map(resultStatus -> createResultMessage(resultStatus, dealerResult))
            .collect(joining(GAME_RESULT_DELIMITER));
        System.out.printf(GAMER_RESULT_FORMAT, DEALER_NAME, result);
    }

    private static String createResultMessage(ResultStatus resultStatus,
        DealerResult dealerResult) {
        return dealerResult.statusCount(resultStatus) + resultStatus.getDisplayName();
    }

    private static void printAllPlayerGameResults(PlayerResults playerResults) {
        playerResults.playerNames()
            .forEach(playerName -> printPlayerGameResult(playerName, playerResults));
    }

    private static void printPlayerGameResult(String playerName, PlayerResults playerResults) {
        ResultStatus resultStatus = playerResults.findPlayerResult(playerName);
        System.out.printf(GAMER_RESULT_FORMAT, playerName, resultStatus.getDisplayName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
