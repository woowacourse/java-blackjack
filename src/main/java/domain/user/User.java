package domain.user;

import domain.card.Card;
import domain.card.Cards;

public abstract class User {

    protected static final String CARD = "카드: ";
    private static final String EMPTY = "";
    private static final String RESULT = " - 결과: ";

    protected Cards cards;
    protected final String name;

    protected User(String name) {
        validate(name);
        cards = new Cards();
        this.name = name;
    }

    private void validate(String name) {
        if (EMPTY.equals(name)) {
            throw new IllegalArgumentException("빈 이름이 있습니다.");
        }
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public String getDrawResult() {
        return name + CARD + cards.getCardsDrawResult();
    }

    public abstract boolean isAvailableToDraw();

    public int calculatePoint() {
        return cards.calculatePointAccordingToHasAce();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public String getTotalDrawResult() {
        return getDrawResult() + RESULT + calculatePoint();
    }

    public String getName() {
        return name;
    }
}
