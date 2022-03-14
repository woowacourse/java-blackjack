package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public class Player extends Participant {
    private final String name;

    public Player(String name, HoldCards holdCards) {
        super(holdCards);
        this.name = name;
    }

    public PlayerOutcome match(Dealer dealer) {
        return PlayerOutcome.match(dealer.countCards(), countCards());
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
