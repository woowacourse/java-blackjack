package blackjack.domain;

import blackjack.strategy.CardPicker;
import java.util.ArrayList;
import java.util.List;

public class CardPool {

    private static final int STANDARD_COUNT = 9;

    private final List<Card> cards = new ArrayList<>();

    public CardPool() {
        final Pattern[] values = Pattern.values();

        for (Pattern pattern : values) {
            addAllCardByPattern(pattern);
        }
    }

    private void addAllCardByPattern(Pattern pattern) {
        cards.add(new AceCard(pattern));
        for (int i = 0; i < STANDARD_COUNT; i++) {
            cards.add(new StandardCard(pattern, String.valueOf(i)));
        }
        cards.add(new CourtCard(pattern, "J"));
        cards.add(new CourtCard(pattern, "K"));
        cards.add(new CourtCard(pattern, "Q"));
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
