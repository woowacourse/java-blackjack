package blackjack.view;

import blackjack.domain.Name;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
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
        System.out.printf("%s: %s", dealer.getName(), String.join(",", dealer.getCardsInfos().subList(0, 1)));
        System.out.println();
        for (Gambler gambler : gamblers.getGamblers()) {
            printPlayerCards(gambler);
        }
        System.out.println();
    }

    public static void printPlayerCards(Gambler gambler) {
        System.out.println(getNameAndCardsInfos(gambler.getName(), gambler.getCardsInfos()));
    }

    private static String getNameAndCardsInfos(Name name, List<String> cardsInfos) {
        return String.format("%s: %s", name, String.join(", ", cardsInfos));
    }

    public static void printDealerOneMoreCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Dealer dealer, Gamblers gamblers) {
        System.out.println();
        CardsResult dealerCardsResult = dealer.getCardsResult();
        printNameCardsAndScore(dealer.getName(), dealer.getCardsInfos(), dealerCardsResult);
        for (Gambler gambler : gamblers.getGamblers()) {
            CardsResult playerCardsResult = gambler.getCardsResult();
            printNameCardsAndScore(gambler.getName(), gambler.getCardsInfos(), playerCardsResult);
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
        Map<Gambler, Integer> playersResult = gameResult.getPlayerResults();
        for (Gambler gambler : playersResult.keySet()) {
            printFinalResultByNameAndResult(gambler.getName(), playersResult.get(gambler));
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
