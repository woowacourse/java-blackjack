package domain.user;

import domain.CardHand;
import domain.TrumpCard;
import java.util.List;

public class Player extends User {
    private final String name;

    public Player(String name) {
        super();
        validate(name);
        this.name = name;
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isAtLeastScore(CardHand.MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getAllCard();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    private void validate(String name) {
        if (name.equals("dealer") || name.equals("딜러")) {
            throw new IllegalArgumentException("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        }
    }
}
