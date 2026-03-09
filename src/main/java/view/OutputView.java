package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printDealOut(String playerNames, int drawCount) {
        printNewLine();
        System.out.println("딜러와 " + playerNames + "에게 " + drawCount + "장을 나누었습니다.");
    }

    public void printHands(String participantName, List<String> hands) {
        System.out.println(participantName + "카드: " + String.join(", ", hands));
    }

    public void printHandsWithScore(String participantName, List<String> hands, int score) {
        System.out.println(participantName + "카드: " + String.join(", ", hands) + " - 결과: " + score);
    }

    public void printFinalResult(Map<String, Integer> dealerResult, Map<String, Boolean> playerResult) {
        printNewLine();
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패%s", dealerResult.getOrDefault("승", 0), dealerResult.getOrDefault("패", 0),
                LINE_SEPARATOR);
        for (Entry<String, Boolean> entry : playerResult.entrySet()) {
            System.out.printf("%s: %s%s", entry.getKey(), entry.getValue() ? "승" : "패", LINE_SEPARATOR);
        }
    }

    public void printBustState(String name, int score, int blackjackScore) {
        printNewLine();
        System.out.printf("%s는 %d점이므로 %d점 초과로 버스트입니다.", name, score, blackjackScore);
        printNewLine();
    }

    public void printDealerDrawResult(boolean draw) {
        printNewLine();
        if (draw) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR);
            return;
        }

        System.out.println("딜러는 16을 초과하여 카드를 더 받지 않았습니다." + LINE_SEPARATOR);
    }

    public void printNewLine() {
        System.out.println();
    }
}
