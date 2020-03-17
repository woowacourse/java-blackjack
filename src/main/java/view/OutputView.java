package view;

import domain.result.TotalResult;
import domain.result.WinningResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printFirstDealOutResult(Dealer dealer, Players players) {
        System.out.println(String.format("\n딜러와 %s에게 2장을 나누었습니다.", players.getAllNames()));
        System.out.println(dealer.getName() + "카드: " + dealer.getFirstCard());
        players.getPlayers().forEach(OutputView::printDealOutResult);
        System.out.println();
    }

    public static void printDealOutResult(Player player) {
        System.out.println(player.getName() + "카드: " + printCards(player));
    }

    private static String printCards(User user) {
        return String.join(",", user.getCards());
    }

    public static void printDealerDealOut() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllTotalResult(Dealer dealer, Players players) {
        System.out.println();
        printTotalResult(dealer);
        players.getPlayers()
                .forEach(OutputView::printTotalResult);
        System.out.println();
    }

    private static void printTotalResult(User user) {
        System.out.println(user.getName() + "카드: " + printCards(user)
                + " - 결과 : " + user.calculatePoint());
    }

    public static void printTotalResult(TotalResult totalResult) {
        System.out.println("## 최종 승패");
        printDealerResult(totalResult.getDealerResult());
        printPlayerResult(totalResult.getPlayersResult());
    }

    private static void printDealerResult(Map<WinningResult, Integer> dealerResult) {
        System.out.println("딜러: "
                + dealerResult.entrySet()
                .stream()
                .map(winningResult -> winningResult.getValue() + winningResult.getKey().getResult())
                .collect(Collectors.joining(" ")));
    }

    private static void printPlayerResult(Map<Player, WinningResult> playersResult) {
        playersResult.entrySet()
                .forEach(OutputView::printResult);
    }

    private static void printResult(Map.Entry<Player, WinningResult> result) {
        String name = result.getKey().getName();
        String winningResult = result.getValue().getResult();

        System.out.println(name + ": " + winningResult);
    }
}
