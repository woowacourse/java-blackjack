package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player implements User {
    private static final int BLACK_JACK_SCORE= 21;
    private static final String NAME_INPUT_ERROR_MESSAGE = "이름은 1자 이상이어야 합니다.";

    private final Cards cards;
    private final String name;

    public Player(String name) {
        this.cards = new Cards();
        this.name = validateName(name);
    }

    public boolean isNotBust() {
        return cards.getScore() <= BLACK_JACK_SCORE;
    }

    private String validateName(String name) {
        if (name.trim().length() < 1) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
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
}
