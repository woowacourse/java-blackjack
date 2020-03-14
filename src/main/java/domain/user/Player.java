package domain.user;

import domain.card.Cards;

public class Player extends User {
    private Name name;

    public Player(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("이름이 비어있습니다.");
        }
        this.name = new Name(name);
    }

    @Override
    public boolean canReceiveCard() {
        if (this.isSmallerThan(Cards.BLACKJACK_SCORE)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name.getName();
    }
}
