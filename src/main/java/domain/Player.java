package domain;

import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;
import java.util.List;

public class Player {
    private static final String ERROR_NAME_LENGTH = "[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int BLACKJACK = 21;

    private final String name;
    private final Cards cards;

    private Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public static Player of(String name, Cards cards) {
        validate(name);
        return new Player(name, cards);
    }

    private static void validate(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return calculateScore() <= BLACKJACK;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.toList());
    }
}
