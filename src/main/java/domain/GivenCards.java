package domain;

import java.util.ArrayList;
import java.util.List;

public class GivenCards {
    private final List<Card> givenCards;

    private GivenCards(List<Card> givenCards) {
        this.givenCards = givenCards;
    }

    public static GivenCards createEmpty() {
        return new GivenCards(new ArrayList<>());
    }

    public boolean addUnique(Card randomCard) {
        if (contains(randomCard)) {
            return false;
        }
        givenCards.add(randomCard);
        return true;
    }

    public boolean contains(Card card) {
        return givenCards.contains(card);
    }

    private void validate() {

    }
}
