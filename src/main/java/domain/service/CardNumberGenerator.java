package domain.service;

import domain.model.CardRank;
import domain.model.CardShape;
import util.NumberGenerator;

public class CardNumberGenerator {

    private final NumberGenerator randomRankNumberGenerator;
    private final NumberGenerator randomShapeNumberGenerator;

    public CardNumberGenerator(NumberGenerator randomRankNumberGenerator, NumberGenerator randomShapeNumberGenerator) {
        this.randomRankNumberGenerator = randomRankNumberGenerator;
        this.randomShapeNumberGenerator = randomShapeNumberGenerator;
    }

    public CardRank generateRank() {
        return CardRank.getRank(randomRankNumberGenerator.generate());
    }

    public CardShape generateShape() {
        return CardShape.getShape(randomShapeNumberGenerator.generate());
    }
}
