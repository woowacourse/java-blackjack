package view;

import controller.BlackJackGame;
import model.*;

import java.util.Map;

public class OutputView {

    public static final String DELIMITER = ": ";
    private static final String INITIAL_CARD_COUNT = "2";

    public static void printInitialCards(Players players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n")
                .append(dealer.toString())
                .append("와 ")
                .append(players.getNames())
                .append("에게 ")
                .append(INITIAL_CARD_COUNT)
                .append("장의 카드를 나누었습니다.");
        System.out.println(stringBuilder.toString());
    }

    public static void printUsersCard(Players players, Dealer dealer) {
        printDealerCard(dealer);
        printPlayersCard(players);
        System.out.println();
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCard(player);
        }
    }

    public static void printPlayerCard(User player) {
        System.out.print("\n"+player.toString() + DELIMITER + player.toStringCardHand());
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.print("\n"+dealer.toString() + DELIMITER + dealer.toStringCardHandFirst());
    }

    public static void printDealerDraw(Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n")
                .append(dealer.toString())
                .append("는 ")
                .append(BlackJackGame.SCORE_BOUNDARY)
                .append("이하라 한장의 카드를 더 받았습니다.");
        System.out.println(stringBuilder.toString());
    }

    public static void printFinalCardHandResult(final Players players, final Dealer dealer) {
        System.out.println();
        printPlayerCard(dealer);
        System.out.print(" - 결과: "+dealer.getScore());
        for(Player player : players.getPlayers()){
            printPlayerCard(player);
            System.out.print(" - 결과: "+player.getScore());
        }
    }

    public static void printDealerResult(final Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Result, Integer> result = dealer.getResult();
        stringBuilder.append(dealer.toString())
                .append(": ")
                .append(result.get(Result.WIN))
                .append("승 ")
                .append(result.get(Result.LOSE))
                .append("패");
        System.out.println(stringBuilder.toString());
    }

    public static void printResult(final Players players, final Dealer dealer) {
        System.out.println("\n\n## 최종 승패");
        printDealerResult(dealer);
        printPlayersResult(players);
    }

    private static void printPlayersResult(final Players players) {
        for(Player player : players.getPlayers()){
            System.out.print(player.toString() +": ");
            System.out.println(player.getResult().toString());
        }
    }
}
