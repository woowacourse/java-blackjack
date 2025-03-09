package domain.user;

import domain.CardHand;
import domain.TrumpCard;
import java.util.List;

public class Player extends User {
    public Player(String name) {
        super(name);
        validate(name);
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isImpossibleDraw(CardHand.MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getAllCard();
    }

    private void validate(String name) {
        if (name.equals("dealer") || name.equals("딜러")) {
            throw new IllegalArgumentException("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        }
    }
}
