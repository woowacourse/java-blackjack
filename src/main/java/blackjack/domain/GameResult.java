package blackjack.domain;

import java.util.Map;

public class GameResult {
    public static void getPlayersCardsAndResult(Players players) {

    }

    public static void getResult(Players players) {
        Map<String, Integer> result = players.calculateResult();
        System.out.println("딜러: " + result.get("win") + "승 " + result.get("draw") + "무 " + result.get("lose") + "패");
        players.makeEachPlayerResult();
    }
}
