package view;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import domain.WinningResult;
import java.util.HashMap;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Players players) {
        System.out
            .println("\n딜러와 " + String.join(", ", players.getPlayerNames()) + "에게 2장의 카드를 나누었습니다.");
    }

    public static void printUsersCards(Dealer dealer, Players players) {
        System.out.println("딜러: " + dealer.getCards().getCards().get(0));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        String playerCards = player.getCards().getCards().toString();
        System.out
            .println(player.getName() + ": " + playerCards.substring(1, playerCards.length() - 1));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printUsersCardsAndScore(Dealer dealer, Players players) {
        printUserCardsAndScore("딜러", dealer);
        for (Player player : players.getPlayers()) {
            printUserCardsAndScore(player.getName(), player);
        }
    }

    public static void printUserCardsAndScore(String name, User user) {
        String userCards = user.getCards().getCards().toString();
        System.out.println(name + ": " + userCards.substring(1, userCards.length() - 1)
            + "- 결과: " + user.getCards().getScore());
    }

    public static void printFinalResult(Players players) {
        System.out.println("\n## 최종 승패");
        printDealerFinalResult(players);
        for (Player player : players.getPlayers()) {
            printPlayerFinalResult(player);
        }
    }

    private static void printPlayerFinalResult(Player player) {
        System.out.println(player.getName() + ": " + player.getWinningResult().getName());
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
            dealerWinningResult.put(player.getWinningResult(),
                dealerWinningResult.get(player.getWinningResult()) + 1);
        }
        System.out.print("딜러: ");
        for (WinningResult winningResult : dealerWinningResult.keySet()) {
            if (dealerWinningResult.get(winningResult) != 0) {
                System.out
                    .print(dealerWinningResult.get(winningResult) + winningResult.getName() + " ");
            }
        }
        System.out.println();
    }
}
