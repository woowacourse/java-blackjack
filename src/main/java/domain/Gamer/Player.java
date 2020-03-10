package domain.Gamer;

import domain.card.Card;

import java.util.List;

public class Player extends Gamer {
    private static final String PATTERN = "[a-zA-Z가-힣]*";

    public Player(String name, List<Card> cards) {
        super(name, cards);

        if(isInvalidName(name)) {
            throw new IllegalArgumentException("잘못된 이름입니다.");
        }
    }

    private boolean isInvalidName(String name) {
        return !name.matches(PATTERN);
    }

    @Override
    public boolean isDrawable() { return false; }

    public static boolean isDrawable(YesOrNo yesorNo) {
        return yesorNo.isDrawable();
    }
}
