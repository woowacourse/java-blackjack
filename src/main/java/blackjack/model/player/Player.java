package blackjack.model.player;

import blackjack.model.Money;
import blackjack.model.trumpcard.Deck;
import blackjack.model.trumpcard.TrumpCard;
import java.util.function.Supplier;

public abstract class Player {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    private final String name;
    private final Deck deck;

    public Player(String name) {
        checkNull(name);
        this.name = name.trim();
        this.deck = new Deck();
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    public void initializeDeck(Supplier<TrumpCard> cardSupplier) {
        this.deck.initializeDeck(cardSupplier);
    }

    public void addCard(TrumpCard card) {
        this.deck.add(card);
    }

    public boolean canHit(int criteria) {
        return this.deck.isScoreLessThan(criteria);
    }

    public int countAddedCards() {
        return this.deck.countAddedCards();
    }

    public boolean isBust() {
        return this.deck.isBust();
    }

    public boolean isBlackjack() {
        return this.deck.isBlackjack();
    }

    public int getScore() {
        return deck.sumScore();
    }

    public String getName() {
        return this.name;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
