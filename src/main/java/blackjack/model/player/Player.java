package blackjack.model.player;

import blackjack.model.trumpcard.Deck;
import blackjack.model.trumpcard.TrumpCard;

public abstract class Player {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    protected final String name;
    protected final Deck deck;

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

    public int getScore() {
        return deck.sumScore();
    }

    public void addCard(TrumpCard card) {
        this.deck.add(card);
    }

    public boolean isBust() {
        return this.deck.isBust();
    }

    public String getName() {
        return this.name;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
