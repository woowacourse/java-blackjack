package blackjackgame.view;

import java.util.List;

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
}
