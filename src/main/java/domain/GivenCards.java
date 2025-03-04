package domain;

import java.util.ArrayList;
import java.util.List;

public class GivenCards {
    private final List<Card> givenCards;

    public GivenCards() {
        this.givenCards = new ArrayList<>();
    }

    public void add(Card randomCard) {
        validate();
        givenCards.add(randomCard);
    }

    private void validate() {

    }
}
