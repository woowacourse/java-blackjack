package blackjack.view;

import blackjack.domain.Name;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.Players;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(),
            String.join(", ", players.getPlayerNames()));
        System.out.println();
    }

    public static void printUsersCards(Dealer dealer, Players players) {
        System.out.printf("%s: %s", dealer.getName(), String.join(",", dealer.getFirstCardInfo()));
        System.out.println();
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        System.out.println(getNameAndCardsInfos(player.getName(), player.getCardsInfos()));
    }

    private static String getNameAndCardsInfos(Name name, List<String> cardsInfos) {
        return String.format("%s: %s", name, String.join(", ", cardsInfos));
    }

    public static void printDealerOneMoreCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Dealer dealer, Players players) {
        System.out.println();
        CardsResult dealerCardsResult = dealer.getCardsResult();
        printNameCardsAndScore(dealer.getName(), dealer.getCardsInfos(), dealerCardsResult);
        for (Player player : players.getPlayers()) {
            CardsResult playerCardsResult = player.getCardsResult();
            printNameCardsAndScore(player.getName(), player.getCardsInfos(), playerCardsResult);
        }
    }

    private static void printNameCardsAndScore(Name name, List<String> cardsInfos,
        CardsResult playerCardsResult) {
        System.out.printf("%s - 결과: %s", getNameAndCardsInfos(name, cardsInfos),
            playerCardsResult.getResult());
        System.out.println();
    }

    public static void printFinalResult(Dealer dealer, GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printFinalResultByNameAndResult(dealer.getName(), gameResult.getDealerResult());
        Map<Player, Integer> playersResult = gameResult.getPlayerResults();
        for (Player player : playersResult.keySet()) {
            printFinalResultByNameAndResult(player.getName(), playersResult.get(player));
        }
    }

    private static void printFinalResultByNameAndResult(Name name, Integer result) {
        System.out.printf("%s: %s", name, result);
        System.out.println();
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
