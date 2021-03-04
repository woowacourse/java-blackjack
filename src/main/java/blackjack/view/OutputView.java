package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.Map;

public class OutputView {

    public static void noticeDrawTwoCards(Players players) {
        System.out.println();
        System.out.println(
            players.getDealerName() + "와 " + players.getPlayerNames() + "에게 " + "2장씩 나누었습니다.");
    }

    public static void noticePlayersCards(Players players) {
        System.out.print(players.getPlayersCards());
        System.out.println();
    }

    public static void noticePlayersPoint(Players players) {
        System.out.println();
        GameResult.getPlayersCardsAndResult(players);
    }

    public static void noticeResult(Players players) {
        System.out.println();
        System.out.println("## 최종 승패");
        GameResult.getResult(players);
    }

    public static void printExceptionMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public static void noticeDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGameResultPoint(Gamer gamer) {
        System.out.println(gamer.getInfo() + " - 결과: " + gamer.calculateMaximumPoint());
    }

    public static void printDealerResult(Map<String, Integer> result) {
        System.out.println(
            "딜러: " + result.get("win") + "승 " + result.get("draw") + "무 " + result.get("lose")
                + "패");
    }

    public static void printPlayerResult(Players players) {
        for (Player player : players.getAllPlayers()) {
            System.out.println(player.getName() + ": " + player.getResult());
        }
    }

    public static void noticeGetMoreCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printPlayerInfo(String info) {
        System.out.println(info);
    }
}
