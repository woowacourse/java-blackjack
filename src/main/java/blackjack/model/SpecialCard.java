package blackjack.model;

public class SpecialCard extends Card {
    private static final int SPECIAL_CARD_POINT = 10;
    private final char character;


    public SpecialCard(char character, CardShape shape) {
        super(shape);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public int getPoint() {
        return SPECIAL_CARD_POINT;
    }
}
