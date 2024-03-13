package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.BetMoney;
import blackjackgame.domain.blackjack.DealerRandomCardDrawStrategy;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.card.Deck;

public class Gamer {
    private final CardHolder cardHolder;
    private final BetMaker betMaker;

    private Gamer(CardHolder cardHolder, BetMaker betMaker) {
        this.cardHolder = cardHolder;
        this.betMaker = betMaker;
    }

    public static Gamer createByNameAndBetMoney(String name, Integer betMoney) {
        CardHolder cardHolder = new CardHolder(name, HoldingCards.of());
        BetMaker betMaker = new BetMaker(name, new BetMoney(betMoney));

        return new Gamer(cardHolder, betMaker);
    }

    public void cardHolderDraw(Deck deck) {
        cardHolder.draw(deck, new DealerRandomCardDrawStrategy(cardHolder));
    }

    public void cardHolderDraw(Deck deck, int execution_count) {
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
