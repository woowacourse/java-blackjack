package blackjack.view;

import java.util.List;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printInitialCardDistribution(List<String> playerNames) {
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printEachPlayerCards(String playerName, List<String> cardNames) {
        System.out.println(playerName + "카드: " + String.join(", ", cardNames));
    }

    public void printDealerCard(String cardName) {
        System.out.println("딜러: " + cardName);
    }

    public void printFinalCards(String name, List<String> cardNames, int score) {
        System.out.println(name + "카드: " + String.join(", ", cardNames) + " - 결과: " + score);
    }

    public void printDealerResult(int winCount, int loseCount, int drawCount) {
        System.out.print("딜러: ");
        if (winCount > 0) {
            System.out.print(winCount + "승 ");
        }
        if (loseCount > 0) {
            System.out.print(loseCount + "패 ");
        }
        if (drawCount > 0) {
            System.out.print(drawCount + "무");
        }
        System.out.println();
    }

    public void printPlayerResult(String playerName, String playerResult) {
        System.out.println(playerName + ": " + playerResult);
    }
}
