package util;

import domain.model.CardRank;
import domain.model.CardShape;

public class RandomCardGenerator {
    private final NumberGenerator rankGenerator;
    private final NumberGenerator shapeGenerator;

    public RandomCardGenerator(NumberGenerator rankGenerator, NumberGenerator shapeGenerator) {
        this.rankGenerator = rankGenerator;
        this.shapeGenerator = shapeGenerator;
    }

    public CardRank generateRank() {
        return CardRank.getRank(rankGenerator.generate());
    }

    public CardShape generateShape() {
        return CardShape.getShape(shapeGenerator.generate());
    }
}
