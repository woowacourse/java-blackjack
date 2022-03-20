package blackjack.model.player;

import blackjack.model.trumpcard.Hand;
import blackjack.model.trumpcard.TrumpCard;
import java.util.function.Supplier;

public abstract class Player {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    private final String name;
    private final Hand hand;

    protected Player(String name) {
        checkNull(name);
        this.name = name.trim();
        this.hand = new Hand();
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    protected void initializeHand(Supplier<TrumpCard> cardSupplier) {
        this.hand.initialize(cardSupplier);
    }

    protected void addCard(TrumpCard card) {
        this.hand.add(card);
    }

    protected boolean canHit(int criteria) {
        return this.hand.isScoreLessThan(criteria);
    }

    protected int countAddedCards() {
        return this.hand.countAddedCards();
    }

    protected boolean isBust() {
        return this.hand.isBust();
    }

    protected boolean isBlackjack() {
        return this.hand.isBlackjack();
    }

    protected boolean isScoreLessThen(Player player) {
        return this.hand.isScoreLessThan(player.hand);
    }

    public int getScore() {
        return hand.sumScore();
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hand;
    }
}
