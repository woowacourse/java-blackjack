package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d패";
    private static final String RESULT_GUIDE_MESSAGE = "\n## 최종 승패";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";
    private static final String POSTFIX_INITIAL_PICK_GUIDE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String PREFIX_INITIAL_PICK_GUIDE_MESSAGE = "\n딜러와 ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";
    private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private OutputView() {
    }

    public static void printInitialPickGuideMessage(Players players) {
        System.out.print(PREFIX_INITIAL_PICK_GUIDE_MESSAGE);
        List<String> playerNames = getPlayerNames(players);
        System.out.println(String.join(DELIMITER, playerNames) + POSTFIX_INITIAL_PICK_GUIDE_MESSAGE);
    }

    private static List<String> getPlayerNames(Players players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public static void printPlayersCards(Dealer dealer, Players players) {
        printDealerCards(dealer);
        printPlayersCards(players);
    }

    private static void printDealerCards(Dealer dealer) {
        printPlayerName(dealer);
        printPlayerCards(dealer);
        printNewLine();
    }

    private static void printPlayerName(Player player) {
        String name = player.getName();
        System.out.print(name + NAME_FORMAT);
    }

    private static void printPlayerCards(Player player) {
        List<String> output = getPlayerCards(player);
        System.out.print(String.join(DELIMITER, output));
    }

    public static List<String> getPlayerCards(Player player) {
        List<String> output = new ArrayList<>();
        for (Card card : player.getCards()) {
            output.add(card.getName() + card.getSuit());
        }
        return output;
    }

    private static void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printSinglePlayer(player);
        }
    }

    public static void printSinglePlayer(Player player) {
        printPlayerName(player);
        printPlayerCards(player);
        printNewLine();
    }

    public static void printScore(Player player) {
        printNewLine();
        printPlayerName(player);
        printPlayerCards(player);
        System.out.print(SCORE_GUIDE_MESSAGE + player.getScore().getValue());
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printResult(Map<Player, Integer> result) {
        System.out.println(NEW_LINE + RESULT_GUIDE_MESSAGE);

        for (Map.Entry<Player, Integer> resultEntry : result.entrySet()) {
            printDealerResult(resultEntry, result.size());
            printPlayersResult(resultEntry);
        }
    }

    public static void printDealerResult(Map.Entry<Player, Integer> playerEntry, int size) {
        if (isDealer(playerEntry)) {
            int winCount = playerEntry.getValue();
            int loseCount = size - winCount - 1;
            System.out.printf(DEALER_RESULT_FORMAT + NEW_LINE, winCount, loseCount);
        }
    }

    private static boolean isDealer(Map.Entry<Player, Integer> playerEntry) {
        return playerEntry.getKey().getName().equals("딜러");
    }

    public static void printPlayersResult(Map.Entry<Player, Integer> playerEntry) {
        if (!isDealer(playerEntry)) {
            int winCount = playerEntry.getValue();
            System.out.print(playerEntry.getKey().getName() + COLON);
            System.out.println(resolveOutcome(winCount));
        }
    }

    public static String resolveOutcome(int winCount) {
        if (winCount == 1) {
            return WIN;
        }
        return LOSE;
    }

    public static void printNewLine() {
        System.out.println();
    }
}
