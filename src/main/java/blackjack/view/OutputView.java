package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Players;

public class OutputView {
    public static void noticeDrawTwoCards(Players players) {
        System.out.println();
        System.out.println(players.getDealerName() + "와 " + players.getPlayerNames() + "에게 " + "2장씩 나누었습니다.");
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
}
