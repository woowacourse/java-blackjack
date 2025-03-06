package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Name;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialDistributionPrompt(final List<Name> playerNames) {
        String names = joinToStringByDelimiter(playerNames, ", ");
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
    }

    public static void printGamblerCards(final String name, final List<Card> cards) {
        String ownCards = joinToStringByDelimiter(cards, ", ");
        System.out.printf("%s카드: %s\n", name, ownCards);
    }

    private static <T> String joinToStringByDelimiter(final List<T> components, final String delimiter) {
        return components.stream()
                .map(T::toString)
                .collect(Collectors.joining(delimiter));
    }

    public static void printBusted(final Name playerName) {
        System.out.printf("[Bust] %s의 카드 합이 21을 초과하여 패배하였습니다.\n", playerName);
    }
}
