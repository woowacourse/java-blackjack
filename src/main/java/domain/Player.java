package domain;

import java.util.List;

public abstract class Player {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        cards = new Cards();
    }

    public void receiveCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(name + ": 이름은 2자 이상 10자 이하여야 합니다.");
        }
    }
}
