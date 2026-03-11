package blackjack.view;

import blackjack.domain.ParticipantResult;
import blackjack.domain.ScoreCompareResult;
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
        System.out.println("딜러는 " + cardCount + "장의 카드를 더 뽑았습니다.");
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
