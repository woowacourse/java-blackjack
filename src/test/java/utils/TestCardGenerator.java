package utils;

import domain.card.Card;
import domain.card.CardGenerator;

import java.util.List;

public class TestCardGenerator implements CardGenerator {
    private final List<Card> cardList;

    private TestCardGenerator(List<Card> cardList) {
        this.cardList = cardList;
    }

    public static TestCardGenerator of(List<Card> cardList) {
        return new TestCardGenerator(cardList);
    }

    @Override
    public List<Card> generate() {
        return this.cardList;
    }
}
