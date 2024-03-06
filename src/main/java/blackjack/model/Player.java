package blackjack.model;

import java.util.List;

public class Player {
    private static final int BUST_THRESHOLD = 21;

    protected final String name;
    protected Cards cards;

    public Player(final String name, final Cards cards) {
        validateNullAndEmptyName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    public void receiveCard(final Card card) {
        this.cards = cards.addCard(card);
    }

    public int notifyScore() {
        return cards.calculateScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public boolean isNotBust() {
        return !cards.isBust(BUST_THRESHOLD);
    }

    public int announceCardCount() {
        return cards.countSize();
    }

    public boolean announceBlackJack() {
        return cards.isBlackJack();
    }
}
