package blackjack.player;

import blackjack.card.Card;
import java.util.List;
import java.util.function.Supplier;

public abstract class Player {

    protected final Hand hand;
    private final Name name;

    protected Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    protected Player(String name) {
        this(name, new Hand());
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    public void drawCards(Supplier<Card> cardSupplier, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(cardSupplier.get());
        }
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }

    public boolean hasSameScoreWith(Player opponent) {
        return hand.hasSameScoreWith(opponent.hand);
    }

    public boolean hasHigherScoreThan(Player opponent) {
        return hand.hasHigherScoreThan(opponent.hand);
    }

    public abstract boolean hasDrawableScore();

    public abstract List<Card> revealCardsOnFirstPhase();


    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
