package blackjack.view;

import blackjack.domain.PlayerCardsName;
import blackjack.domain.PlayerFinalCardsScore;
import blackjack.domain.PlayerFinalResult;
import blackjack.domain.ScoreCompareResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printAllUserCards(List<PlayerCardsName> playersCards, String dealerCards) {
        printDealerInitialDrawnCard(dealerCards);
        for (PlayerCardsName playerCardsName : playersCards) {
            printDrawnCards(playerCardsName.playerName(), playerCardsName.cardNames());
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

    public static void printFinalCardScores(List<PlayerFinalCardsScore> playerFinalCardsScores,
                                            List<String> dealerCards,
                                            int dealerScore) {
        System.out.println();
        printFinalDrawnCards("딜러", dealerCards);
        System.out.printf(" - 결과: %d\n", dealerScore);
        for (PlayerFinalCardsScore playerFinalCardsScore : playerFinalCardsScores) {
            printFinalDrawnCards(playerFinalCardsScore.playerName(), playerFinalCardsScore.cardNames());
            System.out.printf(" - 결과: %d\n", playerFinalCardsScore.totalScore());
        }
    }

    public static void printFinalResult(
            Map<ScoreCompareResult, Integer> dealerResult,
            List<PlayerFinalResult> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        int wins = dealerResult.getOrDefault(ScoreCompareResult.DEALER_WIN, 0);
        int loses = dealerResult.getOrDefault(ScoreCompareResult.DEALER_LOSE, 0);
        int pushes = dealerResult.getOrDefault(ScoreCompareResult.PUSH, 0);
        System.out.printf("딜러: %d승 %d무 %d패%n", wins, pushes, loses);

        for (PlayerFinalResult playerResult : playerResults) {
            System.out.println(playerResult.name() + ": " + toKorean(playerResult.scoreCompareResult()));
        }
    }

    private static String toKorean(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return "승";
        }
        if (result == ScoreCompareResult.PLAYER_LOSE) {
            return "패";
        }
        return "무";
    }


}
