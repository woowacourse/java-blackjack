package blackjack.view;

import static blackjack.view.Constant.DENOMINATION_KOREAN;
import static blackjack.view.Constant.SHAPE_KOREAN;

import blackjack.card.Card;
import blackjack.gamer.Gamer;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARDS_FORMAT = "%s: %s";
    private static final String COMMA = ",";

    public void printCards(final Gamer gamer) {
        final String message = makeCardMessage(gamer);
        System.out.println(message);
    }

    private String makeCardMessage(final Gamer gamer) {
        return String.format(
                CARDS_FORMAT,
                gamer.getNickName(),
                gamer.showInitialCards().stream()
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
