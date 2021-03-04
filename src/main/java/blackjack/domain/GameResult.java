package blackjack.domain;

import java.util.Map;

public class GameResult { //TODO: getCards하면 card들만 나오고 여기서 처리
    public static void getPlayersCardsAndResult(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + ": " + dealer.getCards() + " - 결과: " + dealer.calculateMaximumPoint());
        for (Gamer player : players.getPlayers()) {
            System.out.println(player.getName() + "카드: " + player.getCards()
                    + " - 결과: " + player.calculateMaximumPoint());
        }
    }

    public static void getResult(Players players) {
        Map<String, Integer> result = players.calculateResult();    //TODO: 승패 객체화
        System.out.println("딜러: " + result.get("win") + "승 " + result.get("draw") + "무 " + result.get("lose") + "패");
        players.makeEachPlayerResult();
    }
}
