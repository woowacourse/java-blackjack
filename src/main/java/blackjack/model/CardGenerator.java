package blackjack.model;

import blackjack.model.generator.IndexGenerator;

public class CardGenerator {
    private final IndexGenerator indexGenerator;

    public CardGenerator(IndexGenerator indexGenerator) {
        this.indexGenerator = indexGenerator;
    }

    public Card drawCard() {
        int numberIndex = indexGenerator.generate(CardNumber.values().length);
        CardNumber cardNumber = CardNumber.generate(numberIndex);
        int shapeIndex = indexGenerator.generate(CardNumber.values().length);
        CardShape cardShape = CardShape.generate(shapeIndex);
        return new Card(cardNumber, cardShape);
    }
}
