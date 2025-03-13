package blackjack.view;

import static blackjack.view.Constant.DENOMINATION_KOREAN;
import static blackjack.view.Constant.LINE;
import static blackjack.view.Constant.SHAPE_KOREAN;

import blackjack.card.Card;
import blackjack.gamer.Gamer;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARDS_FORMAT = "%s: %s";
    private static final String CARDS_SUM_FORMAT = "%s - 결과: %d";
    private static final String COMMA = ", ";
    private static final String DEALER_HIT_FORMAT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void printCards(final Gamer gamer, final List<Card> gamerCards) {
        final String message = makeCardMessage(gamer, gamerCards);
        System.out.println(message);
    }

    public void printCardsSum(final Gamer gamer, final List<Card> gamerCards, final int sum) {
        final String message = makeCardSumMessage(gamer, gamerCards, sum);
        System.out.println(message);
    }

    public void printDealerHit() {
        System.out.println(LINE + DEALER_HIT_FORMAT);
    }

    public void printEmptyLine() {
        System.out.print(LINE);
    }

    private String makeCardMessage(final Gamer gamer, List<Card> gamerCards) {
        return String.format(
                CARDS_FORMAT,
                gamer.getNickName(),
                gamerCards.stream()
                        .map(card -> getDenominationName(card) + getShapeName(card))
                        .collect(Collectors.joining(COMMA))
        );
    }

    private String makeCardSumMessage(final Gamer gamer, final List<Card> gamerCards, final int sum) {
        return String.format(
                CARDS_SUM_FORMAT,
                makeCardMessage(gamer, gamerCards),
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
