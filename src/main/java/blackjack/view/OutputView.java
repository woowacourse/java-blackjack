package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;

public class OutputView {
    public static void noticeDrawTwoCards(Players players) {
        System.out.println();
        System.out.println(players.getDealerName() + "와 " + players.getPlayerNames() + "에게 " + "2장씩 나누었습니다.");
    }

    public static void noticePlayersCards(Dealer dealer, Players players) {
        System.out.println(dealer.getInfo());
        for (Player player : players.getPlayers()) {
            noticePlayerCards(player);
        }
        System.out.println();
    }

    public static void noticePlayersPoint(Dealer dealer, Players players) {
        System.out.println();
        GameResult.getPlayersCardsAndResult(dealer, players);
    }

    public static void noticeResult(Players players) {
        System.out.println();
        System.out.println("## 최종 승패");
        GameResult.getResult(players);
    }

    public static void noticeDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void noticePlayerCards(Player player) {
        System.out.println(player.getInfo());
    }
}
