package domain.user;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.HandCard;

import java.util.List;

import static domain.util.NullValidator.validateNull;

public abstract class BlackjackUser {
    private static final int FIRST_DRAW_CARD_COUNT = 2;

    private final HandCard handCard = new HandCard();
    private final String name;

    public BlackjackUser(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNull(name);
        validateEmptyValue(name);
    }

    private void validateEmptyValue(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("빈 값이 입력되었습니다.");
        }
    }

    public void firstDraw(CardDeck cardDeck) {
        if (handCard.isNotEmpty()) {
            throw new RuntimeException("이미 뽑아놓은 카드가 있습니다.");
        }
        for (int i = 0; i < FIRST_DRAW_CARD_COUNT; i++) {
            Card card = cardDeck.pick();
            handCard.add(card);
        }
    }

    public void draw(CardDeck cardDeck) {
        Card card = cardDeck.pick();
        handCard.add(card);
    }

    public int getScore() {
        return handCard.getScore();
    }

    public boolean isBust() {
        return handCard.isBust();
    }

    public boolean isBlackjack() {
        return handCard.isBlackjack();
    }

    public abstract boolean isDrawable();
}
