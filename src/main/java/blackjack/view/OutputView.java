package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitResult(final List<String> names) {
        System.out.printf(System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누어주었습니다." + System.lineSeparator(),
                String.join(", ", names));
    }

    public static void printDealerFirstCard(final Card card) {
        System.out.println("딜러: " + card.getNumberName() + card.getSymbolName());
    }

    public static void printCardsAndScore(final Players players) {
        players.getValue().forEach(OutputView::printCardsAndScore);
    }

    public static void printCardsAndScore(Participant participant) {
        System.out.println(participant.getName() + "카드: " + toCardMessage(participant.getCards())
                + " - 결과: " + participant.getScore());
    }

    public static void printCards(final Players players) {
        players.getValue().forEach(OutputView::printCards);
    }

    public static void printCards(final Player player) {
        System.out.println(player.getName() + "카드: " + toCardMessage(player.getCards()));
    }

    private static String toCardMessage(final Cards cards) {
        return cards.getValue().stream()
                .map(card -> card.getNumberName() + card.getSymbolName())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerTurnResult(final int drawCount) {
        if (drawCount == 0) {
            printDealerNotDrawMessage();
            return;
        }

        for (int i = 0; i < drawCount; i++) {
            printDealerDrawMessage();
        }
    }

    private static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    private static void printDealerNotDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러가 16초과여서 카드를 받지않았습니다.");
    }

    public static void printDealerProfit(final double profit) {
        System.out.printf("%n## 최종 수익%n");
        printProfit("딜러", profit);
    }

    public static void printProfit(final String name, final double profit) {
        System.out.print(name + ": ");

        if (profit == (int) profit) {
            System.out.printf("%d%n", (int) profit);
            return;
        }
        System.out.printf("%s%n", profit);
    }

    public static void printError(final String message) {
        System.out.println("[ERROR]: " + message);
    }

    public static void breakLine() {
        System.out.println();
    }
}
