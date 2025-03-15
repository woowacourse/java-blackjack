package blackjack.view;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Names;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String JOIN_DELIMITER = ", ";

    public void printInitialDistributionPrompt(final Names playerNames) {
        String names = joinToStringByDelimiter(playerNames.getNames(), JOIN_DELIMITER);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
    }

    public void printGamblerCards(final Name name, final List<Card> cards) {
        String ownCards = joinToStringByDelimiter(cards, JOIN_DELIMITER);
        System.out.printf("%s카드: %s\n", name, ownCards);
    }

    public void printBusted(final Name playerName) {
        System.out.printf("[Bust] %s의 카드 합이 %d을 초과하여 패배하였습니다.\n", playerName, BLACKJACK);
    }

    public void printDealerDraw() {
        System.out.printf("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printGamblerResult(final Name name, final List<Card> cards, final int score) {
        String ownCards = joinToStringByDelimiter(cards, JOIN_DELIMITER);
        System.out.printf("%s카드: %s - 결과: %d\n", name, ownCards, score);
    }

    private <T> String joinToStringByDelimiter(final List<T> components, final String delimiter) {
        return components.stream()
                .map(T::toString)
                .collect(joining(delimiter));
    }

    public void printProfit(final int dealerProfit, final Map<Name, Integer> playersProfit) {
        System.out.println("## 최종 수익");
        printDealerProfit(dealerProfit);
        printPlayersProfit(playersProfit);
    }

    private void printDealerProfit(final int dealerProfit) {
        System.out.printf("%s: %d\n", DEALER_NAME, dealerProfit);
    }

    private void printPlayersProfit(final Map<Name, Integer> playersProfit) {
        for (Entry<Name, Integer> entry : playersProfit.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
