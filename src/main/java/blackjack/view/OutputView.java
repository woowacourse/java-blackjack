package blackjack.view;

import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;

import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.gambler.Name;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

    public static void printBusted(final Name playerName) {
        System.out.printf("[Bust] %s의 카드 합이 21을 초과하여 패배하였습니다.\n", playerName);
    }

    public static void printDealerDraw() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGamblerResult(final String name, final List<Card> cards, final int score) {
        String ownCards = joinToStringByDelimiter(cards, ", ");
        System.out.printf("%s카드: %s - 결과: %d\n", name, ownCards, score);
    }

    public static void printWinning(WinningDiscriminator winningDiscriminator) {
        System.out.println("##최종 승패");
        Map<WinningType, Integer> dealerWinning = winningDiscriminator.judgeDealerResult();

        Integer winCount = dealerWinning.get(WIN);
        Integer drawCount = dealerWinning.get(DRAW);
        Integer defeatCount = dealerWinning.get(DEFEAT);
        System.out.printf("딜러: %d승 %d무 %d패\n", winCount, drawCount, defeatCount);

        Map<Name, WinningType> playerWinning = winningDiscriminator.judgePlayersResult();
        for (Entry<Name, WinningType> entry : playerWinning.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getDisplayName());
        }
    }

    private static <T> String joinToStringByDelimiter(final List<T> components, final String delimiter) {
        return components.stream()
                .map(T::toString)
                .collect(Collectors.joining(delimiter));
    }
}
