package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printDistributeResult(final List<String> names) {
        String gamblerNames = names.stream()
                .collect(Collectors.joining(","));

        System.out.println(("딜러와 "+ gamblerNames + "에게 2장의 카드를 나누었습니다"));
    }

    public static void printPlayerCardsInformation(final String name, final Cards cards) {
        printMessageByFormat("%s카드: %s", name, makeCardInfo(cards));
        printLineSeparator();
    }

    private static String makeCardInfo(final Cards cards) {
        return cards.getCards().stream()
                .map(card -> card.getDenominationValue() + card.getSuitValue())
                .collect(Collectors.joining(", "));
    }

    public static void informDealerReceived() {
        System.out.println(("딜러는 16이하라 한장의 카드를 더 받았습니다."));
    }

    public static void printFinalRevenue(final Dealer dealer, final Gamblers gamblers) {
        System.out.println("## 최종 수익");
        System.out.println(dealer.getNameValue() + ": " + dealer.getMoneyValue());
        for (Gambler gambler : gamblers) {
            System.out.println(gambler.getNameValue() + ": " + gambler.getMoneyValue());
        }
    }

    public static void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }
}
