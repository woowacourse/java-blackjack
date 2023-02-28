package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trump {
    private List<Card> cards = new ArrayList<>();

    public Trump() {
        generateTrump();
    }

    private void generateTrump() {
        List<TrumpShape> trumpShapes = Arrays.asList(TrumpShape.values());
        for (TrumpShape trumpShape : trumpShapes) {
            generateTrumpForEachShape(trumpShape);
        }
    }

    private void generateTrumpForEachShape(TrumpShape trumpShape) {
        List<TrumpNumber> trumpNumbers = Arrays.asList(TrumpNumber.values());
        for (TrumpNumber trumpNumber : trumpNumbers) {
            cards.add(new Card(trumpShape, trumpNumber));
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
