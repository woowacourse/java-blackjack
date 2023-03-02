package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String INIT = "";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d패";
    private static final String RESULT_GUIDE_MESSAGE = "\n+ ## 최종 승패";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";
    private static final String POSTFIX_INITIAL_PICK_GUIDE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String PREFIX_INITIAL_PICK_GUIDE_MESSAGE = "딜러와 ";

    private OutputView() {
    }

    public static void printGamblersCards(Players players, Dealer dealer) {
        printDealerCards(dealer);
        printPlayersCards(players);
    }

    private static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printSingleGambler(player);
        }
    }

    public static void printSingleGambler(Gambler gambler) {
        printGamblerName(gambler);
        printGamblerCards(gambler);
    }

    private static void printDealerCards(Dealer dealer) {
        printGamblerName(dealer);
        printGamblerCards(dealer);
    }

    private static void printGamblerName(Gambler gambler) {
        String name = gambler.getName();
        System.out.print(name + NAME_FORMAT);
    }

    private static void printGamblerCards(Gambler gambler) {
        List<String> output = getPlayerCards(gambler);
        System.out.println(String.join(DELIMITER, output));
    }

    public static List<String> getPlayerCards(Gambler gambler) {
        List<String> output = new ArrayList<>();
        for (Card card : gambler.getCards()) {
            output.add(card.getName() + card.getSuit());
        }
        return output;
    }

    public static void printScore(Gambler gambler) {
        printGamblerName(gambler);
        printGamblerCards(gambler);
        System.out.print(SCORE_GUIDE_MESSAGE);
        System.out.println(gambler.getScore());
    }

    public static void printResult(Map<Gambler, Integer> result) {
        System.out.println(RESULT_GUIDE_MESSAGE);


        for (Map.Entry<Gambler, Integer> resultEntry : result.entrySet()) {
            printDealerResult(resultEntry, result.size());
            printPlayersResult(resultEntry);
        }
    }

    public static void printPlayersResult(Map.Entry<Gambler, Integer> gamblerEntry) {
        if (!isDealer(gamblerEntry)) {
            int winCount = gamblerEntry.getValue();
            System.out.print(gamblerEntry.getKey().getName() + ": ");
            System.out.println(resolveOutcome(winCount));
        }
    }

    private static boolean isDealer(Map.Entry<Gambler, Integer> gamblerEntry) {
        return gamblerEntry.getKey().getName().equals("딜러");
    }

    public static String resolveOutcome(int winCount) {
        if (winCount == 1) {
            return WIN;
        }
        return LOSE;
    }

    public static void printDealerResult(Map.Entry<Gambler, Integer> gamblerEntry, int size) {
        if (isDealer(gamblerEntry)) {
            int winCount = gamblerEntry.getValue();
            int loseCount = size - winCount - 1;
            System.out.printf(DEALER_RESULT_FORMAT, winCount, loseCount);
        }
        System.out.println();
    }

    public static void printInitialPickGuideMessage(Players players) {
        System.out.print(PREFIX_INITIAL_PICK_GUIDE_MESSAGE);
        List<String> message = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            message.add(player.getName());
        }
        System.out.println(String.join(DELIMITER, message) + POSTFIX_INITIAL_PICK_GUIDE_MESSAGE);
    }
}