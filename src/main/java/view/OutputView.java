package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import model.blackjackgame.BlackjackGame;
import model.result.ResultStatus;
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
    private static final String HAND_DELIMITER = ", ";
    private static final String GAME_RESULT_DELIMITER = " ";
    private static final String INIT_HAND_INTRO = NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String HAND_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String FINAL_SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 승패";
    private static final String GAMER_RESULT_FORMAT = NEWLINE + "%s: %s";
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printInitHand(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printInitHandIntro(dealer, players);
        printDealerFirstCard(dealer);
        printAllPlayerHands(players);
    }

    private static void printInitHandIntro(Dealer dealer, Players players) {
        String playerNames = String.join(HAND_DELIMITER, players.names());
        System.out.printf(INIT_HAND_INTRO, dealer.getName(), playerNames);
    }

    private static void printDealerFirstCard(Dealer dealer) {
        String name = dealer.getName();
        Card firstCard = dealer.getFirstCard();
        System.out.printf(HAND_FORMAT, name, firstCard);
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
            .collect(collectingAndThen(toList(), result -> String.join(HAND_DELIMITER, result)));
    }

    public static void printAfterDealerHit(Dealer dealer) {
        System.out.printf(DEALER_HIT, dealer.getName());
    }

    public static void printFinalScore(BlackjackGame blackjackGame, GameResult gameResult) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printDealerFinalScore(dealer, gameResult);
        printAllPlayerFinalScores(players, gameResult);
    }

    private static void printDealerFinalScore(Dealer dealer, GameResult gameResult) {
        String handMessage = createHandMessage(dealer.getHand());
        System.out.printf(FINAL_SCORE_FORMAT, dealer.getName(), handMessage, gameResult.findDealerScore());
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
            .collect(joining(GAME_RESULT_DELIMITER));
        System.out.printf(GAMER_RESULT_FORMAT, dealerResult.getName(), result);
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
