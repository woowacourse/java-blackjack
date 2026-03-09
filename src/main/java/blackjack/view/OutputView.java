package blackjack.view;

import blackjack.domain.ScoreCompareResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printAllUserCards(Map<String, List<String>> playerCards, String dealerCards) {
        printDealerInitialDrawnCard(dealerCards);
        for (String playerName : playerCards.keySet()) {
            printDrawnCards(playerName, playerCards.get(playerName));
        }
        System.out.println();
    }

    public static void printInitialCardsDistribution(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장씩 나누었습니다.");
    }

    public static void printDrawnCards(String playerName, List<String> cardNames) {
        System.out.println(playerName + "카드: " + String.join(", ", cardNames));
    }

    public static void printDealerInitialDrawnCard(String cardName) {
        System.out.println("딜러카드: " + cardName);
    }

    private static void printFinalDrawnCards(String playerName, List<String> cardNames) {
        System.out.print(playerName + "카드: " + String.join(", ", cardNames));
    }

    public static void printDealerCardDrawnResult(int cardCount) {
        System.out.println();
        System.out.println("딜러는 " + cardCount + "장의 카드를 더 뽑았습니다.");
    }

    public static void printFinalCardScores(Map<String, List<String>> playerCards, List<String> dealerCards,
                                            Map<String, Integer> playerScores, int dealerScore) {
        System.out.println();
        printFinalDrawnCards("딜러", dealerCards);
        System.out.printf(" - 결과: %d\n", dealerScore);
        for (String playerName : playerCards.keySet()) {
            printFinalDrawnCards(playerName, playerCards.get(playerName));
            System.out.printf(" - 결과: %d\n", playerScores.get(playerName));
        }
    }

    public static void printFinalResult(
            Map<ScoreCompareResult, Integer> dealerResult,
            Map<String, ScoreCompareResult> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        int wins = dealerResult.getOrDefault(ScoreCompareResult.DEALER_WIN, 0);
        int losses = dealerResult.getOrDefault(ScoreCompareResult.DEALER_LOSS, 0);
        int pushes = dealerResult.getOrDefault(ScoreCompareResult.PUSH, 0);
        System.out.printf("딜러: %d승 %d무 %d패%n", wins, pushes, losses);

        for (Map.Entry<String, ScoreCompareResult> entry : playerResults.entrySet()) {
            System.out.println(entry.getKey() + ": " + toKorean(entry.getValue()));
        }
    }

    private static String toKorean(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return "승";
        }
        if (result == ScoreCompareResult.PLAYER_LOSS) {
            return "패";
        }
        return "무";
    }


}
