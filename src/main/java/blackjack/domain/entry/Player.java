package blackjack.domain.entry;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public class Player implements Participant {
    private final String name;
    private final HoldCards holdCards;

    public Player(String name, HoldCards holdCards) {
        this.name = name;
        this.holdCards = holdCards;
    }

    @Override
    public int countCards() {
        return holdCards.countBestNumber();
    }

    @Override
    public void putCard(Card card) {
        holdCards.addCard(card);
    }

    @Override
    public List<Card> openCard() {
        return holdCards.getCards();
    }

    public Outcome isWin(int dealerTotal) {
        return Outcome.match(dealerTotal, countCards());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HoldCards getHoldCards() {
        return holdCards;
    }
}
