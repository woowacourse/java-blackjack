package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String RESULT_GUIDE_MESSAGE = "\n## 최종 승패";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";
    private static final String POSTFIX_INITIAL_PICK_GUIDE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String PREFIX_INITIAL_PICK_GUIDE_MESSAGE = "\n딜러와 ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER = "딜러";
    private static final String BUSTED_RESULT_GUIDE_MESSAGE = "Busted";

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
        System.out.println(NEW_LINE);
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
        System.out.println(NEW_LINE);
    }

    public static void printScore(Player player) {
        System.out.println(NEW_LINE);
        printPlayerName(player);
        printPlayerCards(player);
        System.out.print(SCORE_GUIDE_MESSAGE);
        if (player.isBustedPlayer()) {
            System.out.println(BUSTED_RESULT_GUIDE_MESSAGE);
            return;
        }
        System.out.println(player.getScore().getValue());
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printResult(List<DealerStatus> dealerStats, Players players) {
        System.out.println();
        System.out.println(RESULT_GUIDE_MESSAGE);
        printDealerResult(dealerStats);
        printPlayersResult(dealerStats, players);
    }

    private static void printDealerResult(final List<DealerStatus> dealerStats) {
        StringBuilder dealerStatView = new StringBuilder();
        dealerStatView.append(DEALER).append(COLON);
        Map<DealerStatus, Long> dealerResult = dealerStats.stream()
                .collect(groupingBy(stat -> stat, counting()));
        parseDealerStatus(dealerStatView, dealerResult);
        System.out.println(dealerStatView);
    }

    private static void parseDealerStatus(final StringBuilder dealerStatView, final Map<DealerStatus, Long> dealerResult) {
        Long winCount = dealerResult.get(DealerStatus.WIN);
        Long drawCount = dealerResult.get(DealerStatus.DRAW);
        Long loseCount = dealerResult.get(DealerStatus.LOSE);
        if (winCount != null) {
            dealerStatView.append(winCount).append("승");
        }
        if (drawCount != null) {
            dealerStatView.append(drawCount).append("무");
        }
        if (loseCount != null) {
            dealerStatView.append(loseCount).append("패");
        }
    }

    private static void printPlayersResult(final List<DealerStatus> dealerStats, final Players players) {
        int index = 0;
        for (Player player : players.getPlayers()) {
            System.out.print(player.getName() + COLON);
            parsePlayerStatus(dealerStats, index++);
        }
    }

    private static void parsePlayerStatus(final List<DealerStatus> dealerStats, final int index) {
        if (dealerStats.get(index).equals(DealerStatus.WIN)) {
            System.out.println("패");
            return;
        }
        if (dealerStats.get(index).equals(DealerStatus.DRAW)) {
            System.out.println("무");
            return;
        }
        if (dealerStats.get(index).equals(DealerStatus.LOSE)) {
            System.out.println("승");
        }
    }
}
