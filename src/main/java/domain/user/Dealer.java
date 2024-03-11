package domain.user;

import domain.deck.DealerDeck;

public class Dealer extends User {
    Dealer() {
        super(new DealerDeck());
    }

    public boolean isAddable() {
        return userDeck.isAddable();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public String getNameValue() {
        throw new IllegalStateException("딜러 이름을 불러오는 방식이 잘못되었습니다.");
    }
}
