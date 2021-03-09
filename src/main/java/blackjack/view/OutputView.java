package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultStatistics;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static String NEW_LINE = System.lineSeparator();
    public static String DEALER_PREFIX = "딜러: ";

    public static void printInitSetting(final Challengers challengers) {
        List<String> challengerNames = challengers.toList()
                .stream()
                .map(Challenger::getName)
                .collect(Collectors.toList());

        System.out.printf(NEW_LINE + "딜러와 %s 에게 2장의 카드를 나누어주었습니다.%n", String.join(", ", challengerNames));
    }

    public static void printInitCards(final Dealer dealer, final Challengers challengers) {
        printDealerInitCard(dealer);
        printChallengersInitCards(challengers);
    }

    public static void printDealerReceiveMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayerCards(final Player player) {
        System.out.print(player.getName() + "카드: ");
        List<String> challengersCards = player
                .getHand()
                .stream()
                .map(card -> card.getFaceValue() + card.getSuit()).collect(Collectors.toList());
        System.out.print(String.join(", ", challengersCards));
    }

    public static void printNewLine() {
        System.out.print(NEW_LINE);
    }

    public static void printResult(final List<Player> players) {
        for (Player player : players) {
            printPlayerCards(player);
            System.out.println(" - 결과: " + player.getScore());
        }
        printNewLine();
    }

    public static void printSummary(final ResultStatistics resultStatistics, final Challengers challengers) {
        System.out.println("## 최종 수익");
        System.out.print(DEALER_PREFIX);
        System.out.println(resultStatistics.getDealerProfit());
        challengers.toList().forEach(challenger ->
                System.out.println(challenger.getName() + ": " + resultStatistics.getChallengerProfit(challenger)));
    }

    private static void printChallengersInitCards(final Challengers challengers) {
        for (Challenger challenger : challengers.toList()) {
            printPlayerCards(challenger);
            printNewLine();
        }
        printNewLine();
    }

    private static void printDealerInitCard(final Dealer dealer) {
        System.out.print(DEALER_PREFIX);
        List<Card> dealerCards = dealer.getInitCards();
        dealerCards.forEach(dealerCard -> System.out.println(dealerCard.getFaceValue() + dealerCard.getSuit()));
    }
}
