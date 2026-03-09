package blackjack.view;

import blackjack.domain.GameResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameSettingMessage(String dealerName, List<String> playersName) {
        System.out.println();
        System.out.println(dealerName + "와 " + String.join(", ", playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printSettingCardsResult(String userName, List<String> cards) {
        System.out.println(userName + "카드: " + String.join(", ", cards));
    }

    public static void printGetMoreCardsForDealer(String dealerName) {
        System.out.println(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printCardsResult(String userName, List<String> cards, int cardsValue) {
        System.out.println(userName + "카드: " + String.join(", ", cards) + " - 결과: " + cardsValue);
    }

    public static void printWinningResult(GameResult gameResult, String dealerName) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerName, gameResult);
        printUsersResult(gameResult);
    }

    private static void printDealerResult(String dealerName, GameResult gameResult) {
        System.out.println(dealerName + ": " + gameResult.getDealerWinCount() + "승 " + gameResult.getUserWinCount() + "패");
    }

    private static void printUsersResult(GameResult gameResult) {
        for (Map.Entry<String, Boolean> entry : gameResult.getEntries()) {
            System.out.println(entry.getKey() + ": " + toWinFlag(entry.getValue()));
        }
    }

    private static String toWinFlag(boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }
}
