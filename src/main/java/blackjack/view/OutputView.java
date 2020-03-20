package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Dealer dealer, Gamblers gamblers) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(),
            String.join(", ", gamblers.getPlayerNames()));
        System.out.println();
    }

    public static void printUsersCards(Dealer dealer, Gamblers gamblers) {
        System.out
            .printf("%s: %s", dealer.getName(), String.join(",", dealer.getFirstTimeCardsInfo()));
        System.out.println();
        for (Gambler gambler : gamblers.getGamblers()) {
            System.out.println(getPlayerCardsInfos(gambler, gambler.getFirstTimeCardsInfo()));
        }
        System.out.println();
    }

    public static void printPlayerCards(Gambler gambler) {
        System.out.println(getPlayerCardsInfos(gambler, gambler.getCardsInfos()));
    }

    private static <T extends Player> String getPlayerCardsInfos(T player,
        List<String> cardsInfos) {
        return String.format("%s: %s", player.getName(), String.join(", ", cardsInfos));
    }

    public static void printDealerOneMoreCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Dealer dealer, Gamblers gamblers) {
        System.out.println();
        printUserCardsAndScore(dealer);
        for (Gambler gambler : gamblers.getGamblers()) {
            printUserCardsAndScore(gambler);
        }
    }

    private static <T extends Player> void printUserCardsAndScore(T player) {
        CardsResult playerResult = player.getCardsResult();
        System.out.printf("%s - 결과: %s", getPlayerCardsInfos(player, player.getCardsInfos()),
            playerResult.getResult());
        System.out.println();
    }

    public static void printFinalResult(GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printFinalResultByNameAndResult(gameResult.getDealerResult());
        printFinalResultByNameAndResult(gameResult.getGamblerResults());
    }

    private static <T extends Player> void printFinalResultByNameAndResult(
        Map<T, Integer> playersResults) {
        for (T player : playersResults.keySet()) {
            System.out.printf("%s: %s", player.getName(), playersResults.get(player));
            System.out.println();
        }
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
