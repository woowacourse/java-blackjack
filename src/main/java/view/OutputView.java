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
    public static final String DELIMITER = ": ";
    public static final String NEW_LINE = "\n";
    public static final String RESULT_STRING = " - 결과: ";

    public static void printInitialCards(Players players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n")
                .append(dealer.getName())
                .append("와 ")
                .append(players.getNames())
                .append("에게 ")
                .append(INITIAL_DRAW_COUNT)
                .append("장의 카드를 나누었습니다.");
        System.out.println(stringBuilder.toString());
    }

    public static void printUsersCard(Players players, Dealer dealer) {
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.print(NEW_LINE + dealer.getName() + DELIMITER + dealer.toStringCardHandFirst());
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(BlackJackPerson blackJackPerson) {
        System.out.print(NEW_LINE + blackJackPerson.getName() + DELIMITER + blackJackPerson.toStringCardHand());
    }

    public static void printDealerDraw(Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(NEW_LINE)
                .append(dealer.getName())
                .append("는 ")
                .append(HIT_BOUNDARY)
                .append("이하라 한장의 카드를 더 받았습니다.");
        System.out.println(stringBuilder.toString());
    }

    public static void printFinalCardHandResult(Players players, Dealer dealer) {
        System.out.println();
        printPlayerCard(dealer);
        System.out.print(RESULT_STRING + dealer.getScore());
        for (Player player : players) {
            printPlayerCard(player);
            System.out.print(RESULT_STRING + player.getScore());
        }
    }

    public static void printDealerResult(Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Result, Integer> result = dealer.getResult();
        stringBuilder.append(dealer.getName())
                .append(": ")
                .append(result.get(Result.WIN))
                .append("승 ")
                .append(result.get(Result.LOSE))
                .append("패");
        System.out.println(stringBuilder.toString());
    }

    public static void printResult(Players players, Dealer dealer) {
        System.out.println(NEW_LINE + NEW_LINE + "## 최종 승패");
        printDealerResult(dealer);
        printPlayersResult(players);
    }

    private static void printPlayersResult(Players players) {
        for (Player player : players) {
            System.out.print(player.getName() + DELIMITER);
            System.out.println(player.getResult().toString());
        }
    }
}
