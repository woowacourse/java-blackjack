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

    public void add(Card randomCard) {
        validate();
        givenCards.add(randomCard);
    }

    private void validate() {

    }
}
