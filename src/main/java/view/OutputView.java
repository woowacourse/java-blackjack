package view;

import model.*;

import java.util.Map;
import model.user.BlackJackPerson;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;
import static controller.BlackJackGame.HIT_BOUNDARY;

public class OutputView {

    public static final String NEW_LINE = "\n";
    private static final String DELIMITER = ": ";
    private static final String CARD_DELIMITER = "카드: ";
    private static final String INITIAL_SET_MESSAGE = "%s와 %s에게 %d장의 카드를 나누었습니다.";
    private static final String DEALER_DRAW_MESSAGE = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_CARD_SCORE_MESSAGE = "%s카드: %s - 결과: %d";
    private static final String FINAL_WIN_OR_LOSE_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_MESSAGE = "%s:  %d승 %d무 %d패";

    public static void printInitialCards(Players players, Dealer dealer) {
        System.out.printf(NEW_LINE + INITIAL_SET_MESSAGE + NEW_LINE,
            dealer.getName(), players.getNames(), INITIAL_DRAW_COUNT);
    }

    public static void printUserCard(Players players, Dealer dealer) {
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.println(dealer.getName() + DELIMITER + dealer.toStringCardHandFirst());
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(BlackJackPerson blackJackPerson) {
        System.out.println(
            blackJackPerson.getName() + CARD_DELIMITER + blackJackPerson.toStringCardHand());
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.printf(NEW_LINE + DEALER_DRAW_MESSAGE + NEW_LINE,
            dealer.getName(), HIT_BOUNDARY);
    }

    public static void printFinalScoreResult(Players players, Dealer dealer) {
        System.out.println();
        printScoreResult(dealer);
        for (Player player : players) {
            printScoreResult(player);
        }
    }

    private static void printScoreResult(BlackJackPerson blackJackPerson) {
        System.out.printf(RESULT_CARD_SCORE_MESSAGE + NEW_LINE,
            blackJackPerson.getName(),
            blackJackPerson.toStringCardHand(),
            blackJackPerson.getScore());
    }

    public static void printResult(Players players, Dealer dealer) {
        System.out.println(NEW_LINE + FINAL_WIN_OR_LOSE_MESSAGE);
        printDealerResult(dealer);
        printPlayersResult(players);
    }

    private static void printDealerResult(Dealer dealer) {
        Map<Result, Integer> result = dealer.getResult();
        System.out.printf(DEALER_RESULT_MESSAGE,
            dealer.getName(),
            result.get(Result.WIN),
            result.get(Result.DRAW),
            result.get(Result.LOSE));
    }

    private static void printPlayersResult(Players players) {
        for (Player player : players) {
            System.out.println(player.getName() + DELIMITER + player.getResult().toString());
        }
    }
}
