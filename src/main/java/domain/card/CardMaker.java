package domain.card;

public class CardMaker {
    private final RandomEnumGenerator<Denomination> denominationRandomEnumGenerator;
    private final RandomEnumGenerator<Suit> shapeRandomEnumGenerator;

    public CardMaker() {
        denominationRandomEnumGenerator = new RandomEnumGenerator<>(Denomination.class);
        shapeRandomEnumGenerator = new RandomEnumGenerator<>(Suit.class);
    }

    public String randomMakeCard() {
        Denomination denomination = denominationRandomEnumGenerator.randomEnum();
        Suit shape = shapeRandomEnumGenerator.randomEnum();
        return denomination.getName() + shape.getValue();
    }

    public String fixedMakeCard(String cardName) {
        return cardName;
    }
}
