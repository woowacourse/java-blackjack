package blackjack.view;

import blackjack.domain.PlayerCardsName;
import blackjack.domain.PlayerFinalCardsScore;
import blackjack.domain.PlayerProfitResultDto;
import java.util.List;

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

    public static void printFinalProfitResult(
            double dealerProfit,
            List<PlayerProfitResultDto> playersProfitResults) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", (int) dealerProfit);

        for (PlayerProfitResultDto playerProfitResult : playersProfitResults) {
            System.out.println(playerProfitResult.playerName() + ": " + (int) playerProfitResult.profit());
        }
    }
}
