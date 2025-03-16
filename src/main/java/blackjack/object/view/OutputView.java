package blackjack.object.view;

import static java.util.stream.Collectors.joining;

import blackjack.object.Round;
import blackjack.object.ProfitCalculator;
import blackjack.object.card.Card;
import blackjack.object.gambler.Name;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String JOIN_DELIMITER = ", ";

    public static void printInitialDistributionPrompt(final List<Name> playerNames) {
        String names = joinToStringByDelimiter(playerNames, JOIN_DELIMITER);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
    }

    public static void printGamblerCards(final Name name, final List<Card> cards) {
        String ownCards = joinToStringByDelimiter(cards, JOIN_DELIMITER);
        System.out.printf("%s카드: %s\n", name, ownCards);
    }

    public static void printBusted(final Name playerName) {
        System.out.printf("[Bust] %s의 카드 합이 %d을 초과하여 패배하였습니다.\n", playerName, ProfitCalculator.BLACK_JACK);
    }

    public static void printDealerReceiveCard() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", Round.DEALER_RECEIVE_CRITERIA);
    }

    public static void printGamblerResult(final Name name, final List<Card> cards, final int score) {
        String ownCards = joinToStringByDelimiter(cards, JOIN_DELIMITER);
        System.out.printf("%s카드: %s - 결과: %d\n", name, ownCards, score);
    }

    private static <T> String joinToStringByDelimiter(final List<T> components, final String delimiter) {
        return components.stream()
                .map(T::toString)
                .collect(joining(delimiter));
    }

    public static void printWinning(final ProfitCalculator profitCalculator) {
        System.out.println("##최종 수익");
        printGamblerProfits(profitCalculator);
    }

    private static void printGamblerProfits(ProfitCalculator profitCalculator) {
        Map<Name, Integer> gamlberProfits = profitCalculator.calculateGamblerProfit();
        for (final Entry<Name, Integer> entry : gamlberProfits.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
