package domain.user;

import domain.card.CardDeck;

import java.util.Objects;

public abstract class User {
    private static final int FIRST_DRAW_NUMBER = 2;

    protected final HandCard handCard = new HandCard();
    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public String getName() {
        return name;
    }

    public void draw(CardDeck cardDeck) {
        handCard.add(cardDeck.draw());
    }

    public void firstDraw(CardDeck cardDeck) {
        if (!handCard.isEmpty()) {
            throw new IllegalStateException("잘못된 메서드 호출입니다.");
        }
        for (int i = 0; i < FIRST_DRAW_NUMBER; i++) {
            handCard.add(cardDeck.draw());
        }
    }

    public abstract boolean isDrawable();

    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append(": ")
                .append(handCard.getNames());
        return stringBuilder.toString();
    }

    public int getScore() {
        return handCard.getScore();
    }

    public boolean isOver() {
        return handCard.isOver();
    }
}
