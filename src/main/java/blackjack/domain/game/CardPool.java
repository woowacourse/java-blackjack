package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.strategy.CardPicker;
import java.util.ArrayList;
import java.util.List;

public class CardPool {

    private static final int MIN_INCLUSIVE = 2;
    private static final int MAX_INCLUSIVE = 10;
    private static final List<String> COURT_SYMBOLS = List.of("J", "Q", "K");

    private final List<Card> cards = new ArrayList<>();

    public CardPool() {
        final Pattern[] values = Pattern.values();

        for (Pattern pattern : values) {
            addAllCardByPattern(pattern);
        }
    }

    private void addAllCardByPattern(Pattern pattern) {
        cards.add(new AceCard(pattern));
        addStandardCard(pattern);
        addCourtCard(pattern);
    }

    private void addCourtCard(Pattern pattern) {
        for (String courtSymbol : COURT_SYMBOLS) {
            cards.add(new CourtCard(pattern, courtSymbol));
        }
    }

    private void addStandardCard(Pattern pattern) {
        for (int symbol = MIN_INCLUSIVE; symbol <= MAX_INCLUSIVE; symbol++) {
            cards.add(new StandardCard(pattern, String.valueOf(symbol)));
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card draw(CardPicker cardPicker) {
        final Card card = cardPicker.pick(cards);
        cards.remove(card);
        return card;
    }
}
