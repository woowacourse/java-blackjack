package blackjack.domain.participants;

import blackjack.domain.PlayerCards;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {

    private static final int BUST_LIMIT = 22;

    private final Name name;
    private final PlayerCards playerCards;

    public Participant(final Name name) {
        this.name = name;
        this.playerCards = new PlayerCards();
    }

    public Participant(final String name) {
        this(new Name(name));
    }

    public void receiveCard(final Card card) {
        playerCards.receiveCard(card);
    }

    public Name getName() {
        return name;
    }

    public int getCardCount() {
        return playerCards.getCardCount();
    }

    public List<Card> getPlayerCards() {
        return playerCards.toList();
    }

    public boolean isBust() {
        return calculate() >= BUST_LIMIT;
    }

    public int calculate() {
        return playerCards.calculate();
    }

    public abstract Result decideWinner(final Participant participant);

    public abstract List<Card> initialCards();

    public abstract boolean checkMoreCardAvailable();

}
