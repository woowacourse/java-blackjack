package blackjackgame.domain.gamers;

import blackjackgame.domain.gamers.BetMaker;
import blackjackgame.domain.gamers.CardHolder;

public class Gamer {
    private final CardHolder cardHolder;
    private final BetMaker betMaker;

    public Gamer(CardHolder cardHolder, BetMaker betMaker) {
        this.cardHolder = cardHolder;
        this.betMaker = betMaker;
    }
}
