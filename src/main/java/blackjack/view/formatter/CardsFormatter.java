package blackjack.view.formatter;

import blackjack.model.deck.Card;
import blackjack.view.display.deck.ScoreDisplay;
import blackjack.view.display.deck.ShapeDisplay;
import java.util.List;
import java.util.stream.Collectors;

public class CardsFormatter {
    public static String format(final List<Card> cards) {
        return cards.stream()
                .map(card -> ScoreDisplay.getValue(card.getScore()) + ShapeDisplay.getValue(card.getShape()))
                .collect(Collectors.joining(", "));
    }
}
