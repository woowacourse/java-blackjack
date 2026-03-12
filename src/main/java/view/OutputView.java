package view;

import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    public static void showIntroMessage(List<String> playersName) {
        System.out.println(NEW_LINE + "딜러와 "
                + String.join(DELIMITER, String.join(DELIMITER, playersName)
                + "에게 2장을 나누었습니다."));
    }

    public static void showDealerCardName(List<String> dealerCard) {
        System.out.println(getCardNames("딜러", dealerCard));
    }

    public static void showCardName(String name, List<String> cardNames) {
        System.out.println(getCardNames(name, cardNames));
    }

    private static String getCardNames(String name, List<String> playerCardNames) {
        return name + "카드: " + String.join(DELIMITER, playerCardNames);
    }

    public static void showDealerMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showCardAndScore(String name, List<String> cardNames, int score) {
        System.out.println(getCardNames(name, cardNames) + " - 결과: " + score);
    }

    public static void showGameResult(Map<String, String> playerStatistics, Map<String, Integer> dealerStatistics) {
        System.out.println(NEW_LINE + "## 최종 승패");
        showDealerResult(dealerStatistics);
        System.out.println();

        showPlayerResult(playerStatistics);
    }

    private static void showDealerResult(Map<String, Integer> dealerStatistics) {
        System.out.print("딜러: ");
        for (Map.Entry<String, Integer> entry : dealerStatistics.entrySet()) {
            System.out.print(entry.getValue() + entry.getKey() + " ");
        }
    }

    private static void showPlayerResult(Map<String, String> playerStatistics) {
        for (Map.Entry<String, String> entry : playerStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void showDealerProfitResult(int dealerProfitResult) {
        System.out.println(NEW_LINE + "## 최종 수익");
        System.out.println("딜러: " + dealerProfitResult);
    }

    public static void showPlayerProfitResult(Map<String, Integer> profitResult) {
        for (Map.Entry<String, Integer> entry : profitResult.entrySet()) {
            System.out.printf("%s: %d %s", entry.getKey(), entry.getValue(), NEW_LINE);
        }
    }
}
