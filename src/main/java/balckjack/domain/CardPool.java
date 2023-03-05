package balckjack.domain;

import balckjack.strategy.CardPicker;
import java.util.ArrayList;
import java.util.List;

public class CardPool {

    private static final List<Card> cards = new ArrayList<>();

    private CardPool() {
    }

    static {
        CardPool.init();
    }

    public static void init() {
        cards.clear();
        final Pattern[] values = Pattern.values();

        for (Pattern pattern : values) {
            addAllCardByPattern(pattern);
        }
    }

    private static void addAllCardByPattern(Pattern pattern) {
        cards.add(new AceCard(pattern));
        cards.addAll(StandardCard.createStandardCards(pattern));
        cards.addAll(CourtCard.createCourtCards(pattern));
    }

    public static int getSize() {
        return cards.size();
    }

    public static Card draw(CardPicker cardPicker) {
        final Card card = cardPicker.pick();
        cards.remove(card);
        return card;
    }

    public static List<Card> getCards() {
        return cards;
    }

}
