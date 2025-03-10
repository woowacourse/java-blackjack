package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.util.ArrayList;
import java.util.List;

public class GivenCards {
    private static final int ALL_CARD_COUNT = 52;
    private static final int DEFAULT_CARD_COUNT = 2;
    private static final String NO_ENOUGH_CARD = "카드가 2장 미만으로 남았습니다.";

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
        if (contains(randomCard)) {
            return false;
        }
        givenCards.add(randomCard);
        return true;
    }

    public boolean contains(Card randomCard) {
        return givenCards.contains(randomCard);
    }

    public void checkEnoughUnique() {
        if (isNotEnoughUnique()) {
            throw new IllegalStateException(ERROR_HEADER + NO_ENOUGH_CARD);
        }
    }

    public boolean isNotEnoughUnique() {
        return ALL_CARD_COUNT - givenCards.size() < DEFAULT_CARD_COUNT;
    }
}
