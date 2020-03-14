package view;

import domain.result.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class OutputView {

    public static final String NEWLINE = System.getProperty("line.separator");

    public static void printFirstDealOutResult(Dealer dealer, Players players) {
        System.out.println(String.format(NEWLINE + "딜러와 %s에게 2장을 나누었습니다.", players.getAllNames()));
        System.out.println(dealer.getFirstDrawResult());
        System.out.println(players.getAllFirstDrawResults() + NEWLINE);
    }

    public static void printDealOutResult(Player player) {
        System.out.println(player.getDrawResult());
    }

    public static void printDealerDealOut() {
        System.out.println(NEWLINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE);
    }

    public static void printTotalResult(Dealer dealer, Players players) {
        System.out.println(NEWLINE + dealer.getTotalDrawResult());
        System.out.println(players.getAllTotalDrawResults());
    }

    public static void printWinningResult(GameResult gameResult) {
        System.out.println(NEWLINE + "## 최종 승패");
        System.out.println(gameResult.getTotalResults());
    }
}
