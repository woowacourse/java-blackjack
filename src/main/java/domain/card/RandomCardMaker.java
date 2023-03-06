package domain.card;

public class RandomCardMaker implements CardMaker {
    private final RandomEnumGenerator<Denomination> denominationRandomEnumGenerator;
    private final RandomEnumGenerator<Shape> shapeRandomEnumGenerator;

    public RandomCardMaker() {
        denominationRandomEnumGenerator = new RandomEnumGenerator<>(Denomination.class);
        shapeRandomEnumGenerator = new RandomEnumGenerator<>(Shape.class);
    }

    @Override
    public String makeCard() {
        Denomination denomination = denominationRandomEnumGenerator.randomEnum();
        Shape shape = shapeRandomEnumGenerator.randomEnum();
        return denomination.getName() + shape.getValue();
    }
}
