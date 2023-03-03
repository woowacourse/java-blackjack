package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trump {
    private final List<Card> cards = new ArrayList<>();
    private final NumberGenerator numberGenerator;

    public Trump(NumberGenerator numberGenerator) {
        generateTrump();
        this.numberGenerator = numberGenerator;
    }

    private void generateTrump() {
        for (TrumpShape trumpShape : TrumpShape.values()) {
            generateTrumpForEachShape(trumpShape);
        }
    }

    private void generateTrumpForEachShape(TrumpShape trumpShape) {
        for (TrumpNumber trumpNumber : TrumpNumber.values()) {
            cards.add(new Card(trumpShape, trumpNumber));
        }
    }

    public Card getCard() {
        return cards.remove(numberGenerator.generate(cards.size()));
    }
}
