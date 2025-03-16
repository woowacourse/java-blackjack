package blackjack.view;

import static blackjack.view.Constant.DENOMINATION_KOREAN;
import static blackjack.view.Constant.SHAPE_KOREAN;

import blackjack.card.Card;
import blackjack.gamer.Gamer;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static final String LINE = System.lineSeparator();
    private static final String CARDS_FORMAT = "%s: %s";
    private static final String CARDS_SUM_FORMAT = "%s - 결과: %d";
    private static final String DEALER_HIT_FORMAT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PROFIT_HEAD = "## 최종 수익";
    private static final String PROFIT_FORMAT = "%s: %d";
    private static final String COMMA = ", ";

    public void printCards(final String name, final List<Card> gamerCards) {
        final String message = makeCardMessage(name, gamerCards);
        System.out.println(message);
    }

    public void printCardsSum(final String name, final List<Card> gamerCards, final int sum) {
        final String message = makeCardSumMessage(name, gamerCards, sum);
        System.out.println(message);
    }

    public void printDealerHit() {
        System.out.println(LINE + DEALER_HIT_FORMAT);
    }

    public void printEmptyLine() {
        System.out.print(LINE);
    }

    public void printProfitHead() {
        System.out.println(LINE + PROFIT_HEAD);
    }

    public void printProfit(final Gamer gamer, final long profit) {
        System.out.println(String.format(PROFIT_FORMAT, gamer.getNickName(), profit));
    }

    private String makeCardMessage(final String name, List<Card> gamerCards) {
        return String.format(
                CARDS_FORMAT,
                name,
                gamerCards.stream()
                        .map(card -> getDenominationName(card) + getShapeName(card))
                        .collect(Collectors.joining(COMMA))
        );
    }

    private String makeCardSumMessage(final String name, final List<Card> gamerCards, final int sum) {
        return String.format(
                CARDS_SUM_FORMAT,
                makeCardMessage(name, gamerCards),
                sum
        );
    }

    private String getDenominationName(final Card card) {
        return DENOMINATION_KOREAN.get(card.getDenomination());
    }

    private String getShapeName(final Card card) {
        return SHAPE_KOREAN.get(card.getShape());
    }
}
