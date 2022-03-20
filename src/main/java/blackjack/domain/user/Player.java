package blackjack.domain.user;

import blackjack.domain.card.Cards;

public class Player extends User {

    private final Money money;

    public Player(String name, Money money, Cards cards) {
        super(name, cards);
        this.money = money;
    }

    public Money getBet() {
        return money;
    }

    @Override
    public boolean isHit() {
        return !cards.isBust();
    }

}
