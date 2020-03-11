package domain.gamer;

import exception.NameFormatException;

public class Player extends Gamer {
    private static final String PATTERN = "[a-zA-Z가-힣]*";
    private static final int DRAW_CARD_PIVOT = 21;

    public Player(String name) {
        super(name);

        if (isInvalidName(name)) {
            throw new NameFormatException("잘못된 이름입니다.");
        }
    }

    private boolean isInvalidName(String name) {
        return !name.matches(PATTERN);
    }

    @Override
    public boolean isDrawable() {
        return super.calculateWithAce() < DRAW_CARD_PIVOT;
    }
}
