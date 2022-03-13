package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

import static blackjack.domain.gamer.Gamers.MAX_CARD_VALUE;

public class Player extends Gamer{

    public Player(String name) {
        super(name);
    }

    public BlackJackResult match(Dealer dealer) {
        int playerPoint = getCardsNumberSum();
        int dealerPoint = dealer.getCardsNumberSum();
        return BlackJackResult.of(playerPoint, dealerPoint);
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
