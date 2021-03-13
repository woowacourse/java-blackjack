package blackjack.view;

import blackjack.domain.CardInfoDto;
import blackjack.domain.RevenueInfoDto;
import blackjack.domain.card.Card;
import blackjack.domain.player.Gambler;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printDistributeResult(final List<String> names) {
        String gamblerNames = names.stream()
                .collect(Collectors.joining(","));

        System.out.println(("딜러와 " + gamblerNames + "에게 2장의 카드를 나누었습니다"));
        printLineSeparator();
    }

    public static void printPlayerCardsInformation(final CardInfoDto info) {
        printMessageByFormat("%s카드: %s", info.getName(), makeCardInformation(info.getCards()));
        printLineSeparator();
    }

    private static String makeCardInformation(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenominationValue() + card.getSuitValue())
                .collect(Collectors.joining(", "));
    }

    public static void informDealerReceived() {
        System.out.println(("딜러는 16이하라 한장의 카드를 더 받았습니다."));
        printLineSeparator();
    }

    public static void printFinalRevenue(final RevenueInfoDto info) {
        System.out.println(makeRevenueInfo(info));
        printLineSeparator();
    }

    private static String makeRevenueInfo(final RevenueInfoDto info) {
        return info.getName() + ": " + info.getRevenue();
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
