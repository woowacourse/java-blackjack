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

    public Score getScore() {
        return hand.getScore();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public List<Card> getInitHandCards(int count) {
        return hand.getCards(count);
    }

    public abstract State hit(Card card);

    public abstract State stay();

    public abstract boolean isFinished();

    public abstract boolean isBlackjack();

    public abstract Money calculateProfit(Money bettingMoney, State opponentState);
}
