package blackjack.view;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(final IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void printStartGame(final List<String> playerNames) {
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", playerNames));
    }

    public void printPlayerCardResult(final String name, final List<Card> cards) {
        String cardResult = parseCardToString(cards);
        System.out.printf("%s카드: %s%n", name, cardResult);
    }

    public void printDealerCardResult(final List<Card> cards) {
        Card firstCard = cards.getFirst();
        String cardResult = firstCard.denomination().getText() + firstCard.suit().getText();
        System.out.printf("딜러카드: %s%n", cardResult);
    }

    public void printAddExtraCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void printDealerFinalCardResult(final int sum, final List<Card> cards) {
        String cardResult = parseCardToString(cards);
        System.out.println();
        System.out.printf("딜러카드: %s - 결과: %d%n", cardResult, sum);
    }

    public void printPlayerFinalCardResult(final String name, final int sum, final List<Card> cards) {
        String cardResult = parseCardToString(cards);
        System.out.printf("%s카드: %s - 결과 %d%n", name, cardResult, sum);
    }

    public void printBetResultTitle() {
        System.out.println("## 최종 수익");
    }

    public void printBetResultByDealer(final int winnings) {
        System.out.printf("딜러: %d%n", winnings);
    }

    public void printBetResultByPlayer(final String name, final int winnings) {
        System.out.printf("%s: %d%n", name, winnings);
    }

    private String parseCardToString(final List<Card> cards) {
        return cards
                .stream()
                .map(card -> card.denomination().getText() + card.suit().getText())
                .collect(Collectors.joining(", "));
    }
}
