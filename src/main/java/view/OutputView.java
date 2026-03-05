package view;

import domain.Card;

import domain.Player;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    // 딜러와 pobi, jason에게 2장을 나누었습니다.

    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }


    public void printStartCard(List<Player> players) {
        for (Player player : players) {
            printCurrentHoldCard(player);
        }
        System.out.println();
    }

    public void printCurrentHoldCard(Player player) {
        List<String> startCard = new ArrayList<>();
        for (Card holdCard : player.getHoldCards()) {
            startCard.add(holdCard.toString());
        }
        System.out.println(player.getName() + "카드: " + String.join(", ", startCard));
    }
}
