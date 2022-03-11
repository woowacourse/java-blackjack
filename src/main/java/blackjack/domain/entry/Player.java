package blackjack.domain.entry;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public class Player extends Participant {
    private final String name;

    public Player(String name, HoldCards holdCards) {
        super(holdCards);
        this.name = name;
    }

    public Outcome match(int dealerTotal) {
        return Outcome.match(dealerTotal, countCards());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> openCard() {
        HoldCards holdCards = getHoldCards();
        return List.copyOf(holdCards.getCards());
    }
}