package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final Cards instance = new Cards(gatherCards());

    private List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards getInstance() {
        return instance;
    }

    private static List<Card> gatherCards() {
        List<Card> cards = new ArrayList<>();
        createCards(cards);
        Collections.shuffle(cards);
        return cards;
    }

    private static void createCards(List<Card> cards) {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
