package blackjack.model.player;

import blackjack.model.trumpcard.Hand;
import blackjack.model.trumpcard.TrumpCard;
import java.util.function.Supplier;

public abstract class Player {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    private final String name;
    private final Hand hand;

    public Player(String name) {
        checkNull(name);
        this.name = name.trim();
        this.hand = new Hand();
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    public void initializeHand(Supplier<TrumpCard> cardSupplier) {
        this.hand.initialize(cardSupplier);
    }

    public void addCard(TrumpCard card) {
        this.hand.add(card);
    }

    public boolean canHit(int criteria) {
        return this.hand.isScoreLessThan(criteria);
    }

    public int countAddedCards() {
        return this.hand.countAddedCards();
    }

    public boolean isBust() {
        return this.hand.isBust();
    }

    public boolean isBlackjack() {
        return this.hand.isBlackjack();
    }

    public boolean isScoreLessThen(Player player) {
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
