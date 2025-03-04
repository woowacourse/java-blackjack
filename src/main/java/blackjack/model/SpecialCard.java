package blackjack.model;

public class SpecialCard {
    private final char character;
    private final CardShape shape;

    public SpecialCard(char character, CardShape shape) {
        this.character = character;
        this.shape = shape;
    }

    public char getCharacter() {
        return character;
    }

    public CardShape getShape() {
        return shape;
    }
}
