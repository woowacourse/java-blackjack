package domain.user;

import util.BlackJackRule;

public class Dealer extends User {

    @Override
    public boolean canDrawCard() {
        return BlackJackRule.isDealerDraw(cards.getScore());
    }
}
