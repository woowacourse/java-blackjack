package blackjack.view;

import blackjack.domain.Money;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.PlayerOutcome;
import blackjack.domain.result.GameResult;
import blackjack.domain.gambler.Players;

import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다."
                , dealer.getName()
                , String.join(", ", players.getPlayerNames()));
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
        System.out.println(getPlayerCards(player));
    }

    private static String getPlayerCards(Player player) {
        return String.format("%s: %s", player.getName()
                , String.join(", ", player.getCardsInfos()));
    }

    private static String getDealerCards(Dealer dealer) {
        return String.format("%s: %s", dealer.getName()
                , String.join(", ", dealer.getCardsInfos()));
    }

    public static void printDealerOneMoreCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Dealer dealer, Players players) {
        System.out.println();
        CardsResult dealerCardsResult = dealer.getScore();
        System.out.printf("%s - 결과: %s", getDealerCards(dealer), dealerCardsResult.getResult());
        System.out.println();
        for (Player player : players.getPlayers()) {
            CardsResult playerCardsResult = player.getScore();
            System.out.printf("%s - 결과: %s", getPlayerCards(player), playerCardsResult.getResult());
            System.out.println();
        }
    }

    public static void printFinalResult(Dealer dealer, GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealer,
                gameResult.getDealerResults());
        printPlayerFinalResult(gameResult.getPlayerResults());
    }

    private static void printDealerFinalResult(Dealer dealer, Money dealerMoney) {
        System.out.printf("%s: ", dealer.getName());
        System.out.println(dealerMoney.getMoney());
    }

    private static void printPlayerFinalResult(Map<Player, Money> playersResult) {
        for (Player player : playersResult.keySet()) {
            Money playerMoney = playersResult.get(player);
            System.out.printf("%s: %s", player.getName(), playerMoney.getMoney());
            System.out.println();
        }
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
