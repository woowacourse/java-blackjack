package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultView {
    // 딜러와 플레이어들의 카드 보유 내역
    public static void printStartPlayersCards(Map<String, List<String>> playerCardList) {
        List<String> playerNames = new ArrayList<>();
        for (String playerName : playerCardList.keySet()) {
            if (playerName.equals("딜러")) {
                continue;
            }
            playerNames.add(playerName);
        }

        System.out.println("\n딜러와 " + String.join(", ", playerNames) +"에게 2장을 나누었습니다.");

        for (String playerName : playerCardList.keySet()) {
            List<String> cards = playerCardList.get(playerName);

            if (playerName.equals("딜러")) {
                System.out.println(playerName + "카드: " + cards.get(0));

                continue;
            }

            System.out.println(playerName + "카드: " + String.join(", ", cards));
        }
        System.out.println();
    }

    // 플레이어 한 명의 결과 출력
    public static void printPlayerCards(String name, List<String> playCardList) {
        System.out.println(name + "카드: " + String.join(", ", playCardList));
    }
}
