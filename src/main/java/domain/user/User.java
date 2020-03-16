package domain.user;

import domain.card.CardDeck;
import domain.rule.Drawable;

import java.util.Objects;

public abstract class User {
    public static final int FIRST_DRAW_NUMBER = 2;
    public static final String ERROR_VALIDATE_MESSAGE = "잘못된 입력입니다.";

    protected final HandCard handCard = new HandCard();
    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    private void validateDrawable(Drawable drawable) {
        if (Objects.isNull(drawable)) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MESSAGE);
        }
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MESSAGE);
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

    public boolean isDrawable(Drawable drawable) {
        validateDrawable(drawable);
        return drawable.check(getScore());
    }
}
