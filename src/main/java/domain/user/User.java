package domain.user;

import domain.card.Card;
import domain.card.CardDeck;

import java.util.Objects;

public abstract class User {
    private static final int FIRST_DRAW_CARD_COUNT = 2;

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

    public void firstDraw(CardDeck cardDeck) {
        if (handCard.isNotEmpty()) {
            throw new RuntimeException("이미 뽑아놓은 카드가 있습니다.");
        }
        for (int i = 0; i < FIRST_DRAW_CARD_COUNT; i++) {
            Card card = cardDeck.draw();
            handCard.add(card);
        }
    }

    public void draw(CardDeck cardDeck) {
        Card card = cardDeck.draw();
        handCard.add(card);
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

    public boolean isBust() {
        return handCard.isBust();
    }

    public boolean isBlackJack() {
        return handCard.isBlackJack();
    }

    public abstract boolean isDrawable();
}
