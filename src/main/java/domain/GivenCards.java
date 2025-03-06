package domain;

import java.util.ArrayList;
import java.util.List;

public class GivenCards {
    private static final int ALL_CARD_COUNT = 52;
    private static final int DEFAULT_CARD_COUNT = 2;

    private final List<Card> givenCards;

    private GivenCards(List<Card> givenCards) {
        this.givenCards = givenCards;
    }

    public static GivenCards createEmpty() {
        return new GivenCards(new ArrayList<>());
    }

    public static GivenCards create(List<Card> cards) {
        return new GivenCards(cards);
    }

    public boolean addUnique(Card randomCard) {
        if (givenCards.contains(randomCard)) {
            return false;
        }
        givenCards.add(randomCard);
        return true;
    }

    public boolean notEnoughUnique() {
        return ALL_CARD_COUNT - givenCards.size() < DEFAULT_CARD_COUNT;
    }
}
