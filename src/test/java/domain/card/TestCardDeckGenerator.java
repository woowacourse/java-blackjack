package domain.card;

import java.util.List;

public class TestCardDeckGenerator implements CardDeckInitializer {

    private final List<Card> cardList;

    private TestCardDeckGenerator(List<Card> cardList) {
        this.cardList = cardList;
    }

    public static TestCardDeckGenerator of(List<Card> cardList) {
        return new TestCardDeckGenerator(cardList);
    }

    @Override
    public List<Card> initialize() {
        return this.cardList;
    }

}
