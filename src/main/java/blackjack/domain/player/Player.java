package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    private static final int BUST_THRESHOLD = 21;
    private static final int HIT_THRESHOLD = 16;

    private final Name name;
    private final Hand hand;

    public Player(final String name, final int batMoney) {
        this.name = new Name(name);
        this.hand = new Hand(batMoney);
    }

    public abstract List<Card> getOpenedCards();

    public void addCards(final Cards cards) {
        hand.addCards(cards);
    }

    public boolean isBust() {
        return getCardScore() > BUST_THRESHOLD;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isHit() {
        return hand.calculateScore() <= HIT_THRESHOLD;
    }

    public int getCardScore() {
        return hand.calculateScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int calculateBetAmount(double multiple) {
        return hand.calculateWinningAmount(multiple);
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
