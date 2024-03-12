package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import model.blackjackgame.BlackjackGame;
import model.blackjackgame.ResultStatus;
import model.card.Card;
import model.card.Hand;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayersResult;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_GAME_RESULT = " ";
    private static final String DEALER_NAME = "딜러";
    private static final String INIT_HAND_INTRO =
        NEWLINE + DEALER_NAME + "와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String DEALER_CARD_FORMAT = DEALER_NAME + ": %s" + NEWLINE;
    private static final String HAND_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 승패";
    private static final String GAMER_RESULT_FORMAT = NEWLINE + "%s: %s";
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printInitCards(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printInitHandIntro(players);
        printDealerFirstCard(dealer);
        printAllPlayerHands(players);
    }

    private static void printInitHandIntro(Players players) {
        String playerNames = String.join(DELIMITER, players.names());
        System.out.printf(INIT_HAND_INTRO, playerNames);
    }

    private static void printDealerFirstCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        System.out.printf(DEALER_CARD_FORMAT, firstCard);
    }

    private static void printAllPlayerHands(Players players) {
        players.getGroup()
            .forEach(OutputView::printPlayerCard);
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        String handMessage = createHandMessage(player.getHand());
        System.out.printf(HAND_FORMAT, player.getName(), handMessage);
    }

    private static String createHandMessage(Hand hand) {
        return hand.getCards()
            .stream()
            .map(Card::toString)
            .collect(collectingAndThen(toList(), result -> String.join(DELIMITER, result)));
    }

    public static void printAfterDealerHit() {
        System.out.println(DEALER_HIT);
    }

    public static void printFinalScore(Dealer dealer, Players players, GameResult gameResult) {
        printDealerFinalScore(dealer, gameResult);
        printAllPlayerFinalScores(players, gameResult);
    }

    private static void printDealerFinalScore(Dealer dealer, GameResult gameResult) {
        String handMessage = createHandMessage(dealer.getHand());
        System.out.printf(FINAL_SCORE_FORMAT, DEALER_NAME, handMessage,
            gameResult.findDealerScore());
    }

    private static void printAllPlayerFinalScores(Players players, GameResult gameResult) {
        players.getGroup()
            .forEach(player -> printPlayerFinalScore(player, gameResult));
    }

    private static void printPlayerFinalScore(Player player, GameResult gameResult) {
        String playerName = player.getName();
        String handMessage = createHandMessage(player.getHand());
        System.out.printf(FINAL_SCORE_FORMAT, playerName, handMessage,
            gameResult.findPlayerScore(playerName));
    }

    public static void printGameResult(DealerResult dealerResult, PlayersResult playersResult) {
        System.out.print(GAME_RESULT_INTRO);
        printDealerGameResult(dealerResult);
        printAllPlayerGameResults(playersResult);
    }

    private static void printDealerGameResult(DealerResult dealerResult) {
        String result = dealerResult.allStatus()
            .stream()
            .map(resultStatus -> createResultMessage(resultStatus, dealerResult))
            .collect(joining(DELIMITER_GAME_RESULT));
        System.out.printf(GAMER_RESULT_FORMAT, DEALER_NAME, result);
    }

    private static String createResultMessage(ResultStatus resultStatus,
        DealerResult dealerResult) {
        return dealerResult.statusCount(resultStatus) + resultStatus.getDisplayName();
    }

    private static void printAllPlayerGameResults(PlayersResult playersResult) {
        playersResult.allPlayerName()
                .forEach(playerName -> printPlayerGameResult(playerName, playersResult));
    }

    private static void printPlayerGameResult(String playerName, PlayersResult playersResult) {
        ResultStatus resultStatus = playersResult.findPlayerResult(playerName);
        System.out.printf(GAMER_RESULT_FORMAT, playerName, resultStatus.getDisplayName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
