package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    public BlackJackResult match(Dealer dealer) {
        return BlackJackResult.of(this, dealer);
    }

    public boolean isSameName(String name) {
        return this.getName()
                .equals(name);
    }

    @Override
    boolean canDraw() {
        return getCardsNumberSum() <= MAX_CARD_VALUE;
    }
}
