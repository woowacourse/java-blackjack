package blackjackgame.domain.gamers;

import blackjackgame.domain.card.Deck;
import java.util.List;

public class Gamers {
    private final CardHolders cardHolders;
    private final BetMakers betMakers;

    public Gamers(CardHolders cardHolders, BetMakers betMakers) {
        this.cardHolders = cardHolders;
        this.betMakers = betMakers;
    }

    public void cardHoldersDraw(Deck deck, int execution_count) {
        cardHolders.draw(deck, execution_count);
    }

    public List<String> getRawGamerNames() {
        return cardHolders.getRawPlayerNames();
    }

    public List<CardHolder> getCardHolders() {
        return cardHolders.getCardHolders();
    }

    public List<BetMaker> getBetMakers() {
        return betMakers.getBetMakerGamers();
    }
}
