package balckjack.domain;

import balckjack.strategy.CardPicker;
import java.util.ArrayList;
import java.util.List;

class CardPool {

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
            cards.add(new AceCard(pattern));
            for (int i = 2; i < 11; i++) {
                cards.add(new StandardCard(pattern, String.valueOf(i)));
            }
            cards.add(new CourtCard(pattern, "J"));
            cards.add(new CourtCard(pattern, "K"));
            cards.add(new CourtCard(pattern, "Q"));
        }
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
