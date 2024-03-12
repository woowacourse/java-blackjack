package blackjackgame.domain.gamers;

import blackjackgame.domain.gamers.BetMakers;
import blackjackgame.domain.gamers.CardHolders;

public class Gamers {
    private final CardHolders cardHolders;
    private final BetMakers betMakers;

    public Gamers(CardHolders cardHolders, BetMakers betMakers) {
        this.cardHolders = cardHolders;
        this.betMakers = betMakers;
    }
}
