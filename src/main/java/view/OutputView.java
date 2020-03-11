package view;

import domain.user.Dealer;
import domain.user.Players;

public class OutputView {

    public static void printFirstDealOutResult(Dealer dealer, Players players) {
        System.out.println(String.format("\n딜러와 %s에게 2장을 나누었습니다.", players.getAllNames()));
        System.out.println(dealer.getFirstDrawResult());
        System.out.println(players.getAllFirstDrawResult());
    }
}
