package view;

import domain.*;

import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String INIT = "";

    private OutputView() {
    }

    public static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerName(player);
            printPlayerCards(player);
            System.out.println();
        }
    }

    public static void printPlayerName(Gambler gambler) {
        String name = gambler.getName();
        System.out.print(name + NAME_FORMAT);
    }

    public static void printPlayerCards(Gambler gambler) {
        String output = getPlayerCards(gambler);
        System.out.print(String.join(DELIMITER, output));
    }

    public static String getPlayerCards(Gambler gambler) {
        String output = INIT;
        for (Card card : gambler.getCards()) {
            output += card.getSuit() + card.getName();
        }
        return output;
    }

    public static void printScore(Gambler gambler) {
        printPlayerName(gambler);
        printPlayerCards(gambler);
        System.out.print(" - 결과: ");
        System.out.println(gambler.getScore());
    }

    public static void printResult(Map<Gambler, Integer> result) {
        System.out.println("## 최종 승패");


        for (Map.Entry<Gambler, Integer> resultEntry : result.entrySet()) {
            printDealerResult(resultEntry, result.size());
            printPlayersResult(resultEntry);
        }
        /*
        Controller >> Map<Gambler,승리횟수>
        * */
    }

    private static void printPlayersResult(Map.Entry<Gambler, Integer> resultEntry) {
        if (resultEntry.getKey().getClass().isInstance(Player.class)) {
            int winCount = resultEntry.getValue();
            System.out.print(resultEntry.getKey().getName() + ": ");
            System.out.println(resolveOutcome(winCount));
        }
    }

    private static String resolveOutcome(int winCount) {
        if (winCount == 1) {
            return "승";
        }
        return "패";
    }

    private static void printDealerResult(Map.Entry<Gambler, Integer> gamblerEntry, int size) {
        if (gamblerEntry.getKey().getClass().isInstance(Dealer.class)) {
            int winCount = gamblerEntry.getValue();
            int loseCount = size - winCount - 1;
            System.out.printf("딜러: %d승 %d패", winCount, loseCount);
        }
    }
}