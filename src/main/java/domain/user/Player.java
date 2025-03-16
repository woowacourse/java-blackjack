package domain.user;

import domain.CardHand;
import domain.Card;
import java.util.List;

public class Player extends User {
    private final String name;

    public Player(String name) {
        super();
        validate(name);
        this.name = name;
    }

    @Override
    public boolean isDrawable() {
        return !cardHand.isAtLeastScore(CardHand.MAX_SCORE);
    }

    @Override
    public List<Card> openInitialCard() {
        return this.cardHand.getAllCard();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void validate(String name) {
        if (name.equals("dealer") || name.equals("딜러")) {
            throw new IllegalArgumentException("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        }
    }
}
