package blackjack.view;

import static blackjack.view.Constant.DENOMINATION_KOREAN;
import static blackjack.view.Constant.SHAPE_KOREAN;

import blackjack.card.Card;
import blackjack.gamer.Gamer;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARDS_FORMAT = "%s: %s";
    private static final String COMMA = ", ";

    public void printCards(final Gamer gamer, final List<Card> gamerCards) {
        final String message = makeCardMessage(gamer, gamerCards);
        System.out.println(message);
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

    private String getDenominationName(final Card card) {
        return DENOMINATION_KOREAN.get(card.getDenomination());
    }

    private String getShapeName(final Card card) {
        return SHAPE_KOREAN.get(card.getShape());
    }
}
