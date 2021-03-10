package blackjack.domain.participants;

import blackjack.domain.PlayerCards;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARDS_COUNT = 2;
    private final Name name;
    private final Money money;
    private final PlayerCards playerCards;

    public Participant(final Name name, final Money money) {
        this.name = name;
        this.money = money;
        this.playerCards = new PlayerCards();
    }

    public Participant(final Name name) {
        this.name = name;
        money = new Money();
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

    public Money getMoney() {
        return money;
    }

    public int getCardCount() {
        return playerCards.getCardCount();
    }

    public List<Card> getPlayerCards() {
        return playerCards.toList();
    }

    public boolean isBlackjack() {
        if (isBust()) {
            return false;
        }
        return calculate() == BLACKJACK_SCORE && getCardCount() == INITIAL_CARDS_COUNT;
    }

    public boolean isBust() {
        return calculate() > BLACKJACK_SCORE;
    }

    public int calculate() {
        return playerCards.calculate();
    }

    public boolean isSameScore(final Participant participant) {
        return this.calculate() == participant.calculate();
    }

    public abstract Result decideWinner(final Participant participant);

    public abstract List<Card> initialCards();

    public abstract boolean checkMoreCardAvailable();

}
