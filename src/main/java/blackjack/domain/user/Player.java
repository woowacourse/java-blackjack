package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player implements User {

    private static final int BLACKJACK_UPPER_BOUND = 21;
    private final Cards cards;
    private final String name;

    public Player(String name) {
        this.cards = new Cards();
        this.name = validateName(name);
    }

    private String validateName(String name) {
        if (name.trim().length() < 1) {
            throw new IllegalArgumentException("이름은 1자 이상이어야 합니다.");
        }
        return name.trim();
    }

    @Override
    public void hit(Card card) {
        this.cards.addCard(card);
    }

    @Override
    public String getCards() {
        return this.cards.getCards();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }

    public boolean isNotBust() {
        return cards.getScore() <= BLACKJACK_UPPER_BOUND;
    }
}
