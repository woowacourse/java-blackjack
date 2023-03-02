package blackjackgame.view;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printCards(String playerName, List<List<String>> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator()).append(playerName).append("카드: ");
        for (List<String> card : cards) {
            stringBuilder.append(card.get(0));
            stringBuilder.append(card.get(1));
            stringBuilder.append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(", "));
        System.out.print(stringBuilder);
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printResult(Map<String, Integer> dealerResult, Map<String, String> result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패").append(System.lineSeparator()).append("딜러: ");
        for (String gameOutcome : dealerResult.keySet()) {
            int number = dealerResult.get(gameOutcome);
            if (number != 0) {
                stringBuilder.append(number).append(gameOutcome).append(" ");
            }
        }

        for (String name : result.keySet()){
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(name).append(": ").append(result.get(name));
        }
        System.out.print(System.lineSeparator() + System.lineSeparator() + stringBuilder);
    }
}
