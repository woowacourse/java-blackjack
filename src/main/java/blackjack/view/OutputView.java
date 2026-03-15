package blackjack.view;

import blackjack.domain.ParticipantResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printAllUserCards(Map<String, List<String>> playerCards, List<String> dealerCards) {
        printDrawnCards("딜러", dealerCards);
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

    private static void printFinalDrawnCards(String playerName, List<String> cardNames) {
        System.out.print(playerName + "카드: " + String.join(", ", cardNames));
    }

    public static void printDealerCardDrawnResult(int cardCount) {
        System.out.println();
        System.out.println("딜러는 16이하라 " + cardCount + "장의 카드를 더 뽑았습니다.");
    }

    public static void printFinalCardScores(List<ParticipantResult> playerResult, ParticipantResult dealerResult) {
        System.out.println();

        printFinalDrawnCards(dealerResult.name(), dealerResult.cardNames());
        System.out.printf(" - 결과: %d\n", dealerResult.score());

        for (ParticipantResult result : playerResult) {
            printFinalDrawnCards(result.name(), result.cardNames());
            System.out.printf(" - 결과: %d\n", result.score());
        }
    }

    public static void printFinalProfit(long dealerProfit, Map<String,Long> playersProfit) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + dealerProfit);
        for (Map.Entry<String, Long> entry : playersProfit.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
