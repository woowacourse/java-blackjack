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

    private static final String NEW_LINE = "\n";

    public static void printInitialCards(Players players, Dealer dealer) {
        System.out.printf(NEW_LINE + "%s와 %s에게 %d장의 카드를 나누었습니다." + NEW_LINE,
            dealer.getName(), players.getNames(), INITIAL_DRAW_COUNT);
    }

    public static void printUserCard(Players players, Dealer dealer) {
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.printf("%s: %s" + NEW_LINE,
            dealer.getName(),
            dealer.toStringCardHandFirst());
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(BlackJackPerson blackJackPerson) {
        System.out.printf("%s카드: %s" + NEW_LINE,
            blackJackPerson.getName(),
            blackJackPerson.toStringCardHand());
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.printf(NEW_LINE + "%s는 %d이하라 한장의 카드를 더 받았습니다." + NEW_LINE,
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
        System.out.printf("%s카드: %s - 결과: %d" + NEW_LINE,
            blackJackPerson.getName(),
            blackJackPerson.toStringCardHand(),
            blackJackPerson.getScore());
    }

    public static void printResult(Players players, Dealer dealer) {
        System.out.println(NEW_LINE + "## 최종 승패");
        printDealerResult(dealer);
        printPlayersResult(players);
    }

    private static void printDealerResult(Dealer dealer) {
        Map<Result, Integer> result = dealer.getResult();
        System.out.printf("%s:  %d승 %d무 %d패",
            dealer.getName(),
            result.get(Result.WIN),
            result.get(Result.DRAW),
            result.get(Result.LOSE));
    }

    private static void printPlayersResult(Players players) {
        for (Player player : players) {
            System.out.printf("%s: %s" + NEW_LINE,
                player.getName(), player.getResult().toString());
        }
    }
}
