package domain;

import java.util.List;

public abstract class Player {

    private static final int MAX_NAME_LENGTH = 10;
    private final String name;
    private final Cards cards;

    Player(final String name, final Cards cards) {
        validateBlank(name);
        validateLength(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }

    public List<Card> displayCards() {
        return cards.displayCards();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }
}
