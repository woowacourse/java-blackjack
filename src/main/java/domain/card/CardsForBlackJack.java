package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsForBlackJack {

    public static final CardsForBlackJack cardsForBlackJack = new CardsForBlackJack(gatherCards());

    private List<Card> cards;

    private CardsForBlackJack(List<Card> cards) {
        this.cards = cards;
    }

    public static CardsForBlackJack getInstance() {
        return cardsForBlackJack;
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
