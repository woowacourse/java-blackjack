package domain.gamer;

import domain.calculatestrategy.CalculateStrategy;
import domain.deck.Card;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private final Nickname nickname;
    private final Hand hand;

    public Gamer(final Nickname nickname, final CalculateStrategy calculateStrategy) {
        this.nickname = nickname;
        this.hand = new Hand(calculateStrategy);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isImpossibleDrawCard() {
        return hand.isImpossibleDrawCard();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public void receiveInitialCards(final List<Card> cards) {
        cards.forEach(hand::add);
    }

    public void hit(final Card card) {
        hand.add(card);
    }

    public int getScoreSum() {
        return hand.getScoreSum();
    }

    public String getDisplayName() {
        return nickname.getDisplayName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Gamer gamer)) {
            return false;
        }
        return Objects.equals(nickname, gamer.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}
