package view;

import domain.*;

import java.util.HashMap;
import java.util.Map;

public class OutputView {
    public static final String DELIMITER = ", ";

    public static void printFirstCards(Dealer dealer, Players players) {
        printFirstDrawMessage(players);
        printFirstDealerCards(dealer);
        printPlayersCards(players, false);
        System.out.println();
    }

    private static void printFirstDrawMessage(Players players) {
        System.out.println("\n딜러와 " + String.join(DELIMITER, players.getPlayerNames())
                + "에게 2장의 카드를 나누었습니다.");
    }

    private static void printFirstDealerCards(Dealer dealer) {
        System.out.println("딜러: " + dealer.getCards().getCards().get(0));
    }

    public static void printUsersCards(Dealer dealer, Players players, boolean withScore) {
        printUserCards("딜러", dealer, withScore);
        printPlayersCards(players, withScore);
    }

    public static void printUserCards(String name, User user, boolean withScore) {
        System.out.print(name + ": " + user.getCards());
        if (withScore) {
            System.out.print("- 결과: " + user.getCards().getScore());
        }
        System.out.println();
    }

    public static void printPlayersCards(Players players, boolean withScore) {
        for (Player player : players.getPlayers()) {
            printUserCards(player.getName(), player, withScore);
        }
    }

    public static void printFinalResult(Players players) {
        System.out.println("\n## 최종 승패");
        printDealerFinalResult(players);
        for (Player player : players.getPlayers()) {
            printPlayerFinalResult(player);
        }
    }

    private static void printPlayerFinalResult(Player player) {
        System.out.println(player.getName() + ": " + player.getWinningResult().getKorean());
    }

    public static void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }


    /* todo 수정 필요
       1. 뷰에 둔다.
          - View에 이런거 놔둬도 되나?
       2. 책임을 Dealer로 옮긴다.
          - 1) Player의 WinningResult를 받아온다.
          - 2) Player에게 Dealer를 넘겨서, 셋팅을 해주도록 한다.
          - 3) Player에게 Delaer의 WinningResult를 물어보고 받아온다.
       3. 책임을 바깥(다른 도메인)으로 옮긴다.
          - 이럴 경우에는, Player의 WinningResult는 그대로 놔둬도 되는가?
    */
    private static void printDealerFinalResult(Players players) {
        Map<WinningResult, Integer> dealerWinningResult = new HashMap<>();
        dealerWinningResult.put(WinningResult.WIN, 0);
        dealerWinningResult.put(WinningResult.DRAW, 0);
        dealerWinningResult.put(WinningResult.LOSE, 0);
        for (Player player : players.getPlayers()) {
            dealerWinningResult.put(player.getWinningResult(), dealerWinningResult.get(player.getWinningResult()) + 1);
        }
        System.out.print("딜러: ");
        for (WinningResult winningResult : dealerWinningResult.keySet()) {
            if (dealerWinningResult.get(winningResult) != 0) {
                System.out.print(dealerWinningResult.get(winningResult) + winningResult.getKorean() + " ");
            }
        }
        System.out.println();
    }
}
