package blackjack.model;

public class SpecialCard extends Card {
    private final char character;

    public SpecialCard(char character, CardShape shape) {
        super(shape);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
