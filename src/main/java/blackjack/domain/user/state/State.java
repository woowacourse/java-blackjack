package blackjack.domain.user.state;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.money.Money;
import blackjack.domain.user.Hand;

public abstract class State {

    protected final Hand hand;

    protected State(Hand hand) {
        this.hand = hand;
    }

    public final Score getScore() {
        return hand.getScore();
    }

    public final boolean isBlackjack() {
        return hand.isBlackJack();
    }

    public final List<Card> getHandCards() {
        return hand.getCards();
    }

    public final List<Card> getInitHandCards(int count) {
        return hand.getCards(count);
    }

    public abstract State hit(Card card);

    public abstract State stay();

    public abstract boolean isFinished();

    public abstract Money calculateProfit(Money bettingMoney, State opponentState);
}
