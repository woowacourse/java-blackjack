package blackjack.domain;

import java.util.List;

abstract public class Player {

    private static final int INITIAL_CARD_SIZE = 2;

    private final String name;
    private final Cards cards;

    public Player(final String name, final List<Card> cards) {
        checkNameBlank(name);
        checkInitialCardsSize(cards);
        this.name = name;
        this.cards = new Cards(cards);
    }

    private void checkNameBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void checkInitialCardsSize(final List<Card> cards) {
        if (cards.size() != INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException("[ERROR] 가장 처음에는 카드를 " + INITIAL_CARD_SIZE + "장씩 나눠줘야 합니다.");
        }
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    abstract public boolean canAddCard();
}
