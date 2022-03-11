package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public class Player extends Participant {
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;

    public Player(String name, HoldCards holdCards) {
        super(holdCards);
        this.name = name;
    }

    public PlayerOutcome match(int dealerTotal) {
        return PlayerOutcome.match(dealerTotal, countCards());
    }

    public boolean hasBustCard() {
        return getHoldCards().countBestNumber() > BLACKJACK_NUMBER;
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
