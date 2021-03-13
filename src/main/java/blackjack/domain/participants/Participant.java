package blackjack.domain.participants;

import blackjack.domain.PlayerCards;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARDS_COUNT = 2;
    private final Name name;
    private final double money;
    private final PlayerCards playerCards;

    public Participant(final Name name, final double money) {
        this.name = name;
        this.money = money;
        this.playerCards = new PlayerCards();
    }

    public Participant(final Name name) {
        this.name = name;
        money = 0;
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

    public double getMoney() {
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

    public abstract Result decideWinner(final Participant participant);

    protected Result decideWinnerWithScores(final Participant participant) {
        final int score = this.calculate();
        final int opponentScore = participant.calculate();
        return Result.compareScore(score, opponentScore);
    }

    public abstract List<Card> initialCards();

    public abstract boolean checkMoreCardAvailable();

}
