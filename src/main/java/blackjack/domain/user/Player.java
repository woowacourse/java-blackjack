package blackjack.domain.user;

import blackjack.domain.card.Cards;

public class Player extends User {

    private final Betting betting;

    public Player(String name, Betting betting, Cards cards) {
        super(name, cards);
        this.betting = betting;
    }

    public Betting getBetting() {
        return betting;
    }

    @Override
    public boolean isHit() {
        return !cards.isBust();
    }

}
