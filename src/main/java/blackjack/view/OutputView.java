package blackjack.view;

import static blackjack.domain.Round.BLACK_JACK;
import static blackjack.domain.Round.DEALER_DRAW_THRESHOLD;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static java.util.stream.Collectors.joining;

import blackjack.domain.gambler.Names;
import blackjack.domain.WinningDiscriminator;
import blackjack.domain.card.Card;
import blackjack.domain.gambler.Name;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
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
        System.out.printf("[Bust] %s의 카드 합이 %d을 초과하여 패배하였습니다.\n", playerName, BLACK_JACK);
    }

    public void printDealerDraw() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_DRAW_THRESHOLD);
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

    public void printWinning(final WinningDiscriminator winningDiscriminator) {
        System.out.println("##최종 승패");
        printDealerWinning(winningDiscriminator);
        printPlayerWinning(winningDiscriminator);
    }

    private void printDealerWinning(final WinningDiscriminator winningDiscriminator) {
        Map<WinningType, Integer> dealerWinning = winningDiscriminator.judgeDealerResult();
        Integer winCount = dealerWinning.get(WIN);
        Integer drawCount = dealerWinning.get(DRAW);
        Integer defeatCount = dealerWinning.get(DEFEAT);
        System.out.printf("딜러: %d승 %d무 %d패\n", winCount, drawCount, defeatCount);
    }

    private void printPlayerWinning(final WinningDiscriminator winningDiscriminator) {
        Map<Name, WinningType> playerWinning = winningDiscriminator.judgePlayersResult();
        for (final Entry<Name, WinningType> entry : playerWinning.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getDisplayName());
        }
    }
}
