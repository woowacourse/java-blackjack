package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import model.blackjackgame.BlackjackGame;
import model.result.ResultStatus;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayersResult;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
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

    public static void printFinalScore(BlackjackGame blackjackGame, Players players,
        GameResult gameResult) {
        Dealer dealer = blackjackGame.getDealer();
        printDealerFinalScore(dealer, gameResult);
        printAllPlayerFinalScores(players, gameResult);
    }

    private static void printDealerFinalScore(Dealer dealer, GameResult gameResult) {
        String cardsMessage = createCardsMessage(dealer.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, dealer.getName(), cardsMessage,
            gameResult.findDealerScore());
    }

    private static void printAllPlayerFinalScores(Players players, GameResult gameResult) {
        players.getPlayers()
            .forEach(player -> printPlayerFinalScore(player, gameResult));
    }

    private static void printPlayerFinalScore(Player player, GameResult gameResult) {
        String playerName = player.getName();
        String cardsMessage = createCardsMessage(player.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, playerName, cardsMessage,
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
