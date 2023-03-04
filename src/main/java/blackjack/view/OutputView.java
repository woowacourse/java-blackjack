package blackjack.view;

import blackjack.domain.player.*;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printReadyMessage(List<Name> names) {
        String allName = names.stream().map(Name::getName).collect(Collectors.joining(", "));
        System.out.println("딜러와 " + allName + "2장을 나누었습니다.");
    }

    public static void printPlayersCurrentCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCurrentCards(player);
        }
    }

    public static void printPlayerCurrentCards(Player player) {
        String playerCards = getPlayerCards(player);
        System.out.println(player.getPlayerName() + "카드: " + playerCards);
    }

    private static String getPlayerCards(User user) {
        return user.getPlayerCards().stream()
                .map(card -> card.getNumber().getNumber() + card.getSymbol().getSymbol())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerCurrentCards(Dealer dealer) {
        String dealerCards = dealer.getPlayerCards().stream()
                .map(card -> card.getNumber().getNumber() + card.getSymbol().getSymbol())
                .limit(1)
                .collect(Collectors.joining(""));
        System.out.println("딜러 카드: " + dealerCards);
    }

    public static void printScore(Dealer dealer, Players players) {
        System.out.println("딜러 카드: " + getPlayerCards(dealer) + " - 결과: " + dealer.getTotalScore());
        for (Player player : players.getPlayers()) {
            System.out.println(getPlayerCards(player) + " - 결과: " + player.getTotalScore());
        }
    }

    public static void printResults(HashMap<Player, Result> playerResults, HashMap<Result, Integer> dealerResults) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        for (Result result : dealerResults.keySet()) {
            if (dealerResults.get(result) > 0) {
                System.out.println(dealerResults.get(result) + result.getResult() + " ");
            }
        }
        for (Player player : playerResults.keySet()) {
            System.out.println(player.getPlayerName() + ": " + playerResults.get(player).getResult());
        }
    }

    public static void printDealerOneMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
