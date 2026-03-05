package view;

import domain.Card;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    // 딜러와 pobi, jason에게 2장을 나누었습니다.

    public void printStartCardMessage(List<String> playerNames) {
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
        System.out.println();
    }


    public void printStartCard(String playerName, List<Card> holdsCards) {
        List<String> startCard = new ArrayList<>();
        for (Card holdsCard : holdsCards) {
            startCard.add(holdsCard.toString());
        }
        System.out.println(playerName + "카드: " + String.join(", ", startCard));
    }
}
