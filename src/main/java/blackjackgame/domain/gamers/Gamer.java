package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.DealerRandomCardDrawStrategy;
import blackjackgame.domain.card.Deck;

public class Gamer {
    private final CardHolder cardHolder;
    private final BetMaker betMaker;

    public Gamer(CardHolder cardHolder, BetMaker betMaker) {
        this.cardHolder = cardHolder;
        this.betMaker = betMaker;
    }

    public void cardHolderDrawNTimes(Deck deck, int execution_count) {
        cardHolder.draw(deck, new DealerRandomCardDrawStrategy(cardHolder), execution_count);
    }

    public String getRawGamerName() {
        return cardHolder.getRawName();
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }

    public BetMaker getBetMaker() {
        return betMaker;
    }
}
