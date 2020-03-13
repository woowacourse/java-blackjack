package domain.user;

import domain.card.Card;
import domain.card.Cards;

public abstract class User {

    private static final String EMPTY = "";
    private static final String NAME_ERROR_MESSAGE = "빈 이름이 있습니다.";

    protected Cards cards;
    protected final String name;

    protected User(String name) {
        validate(name);
        cards = new Cards();
        this.name = name;
    }

    private void validate(String name) {
        if (EMPTY.equals(name)) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public String getDrawResult() {
        return name + "카드: " + cards.getCardsDrawResult();
    }

    public int calculatePoint() {
        return cards.calculatePointAccordingToHasAce();
    }

    public abstract boolean isAvailableToDraw();

    public String getTotalDrawResult() {
        return getDrawResult() + " - 결과: " + calculatePoint();
    }

    public abstract String getTotalWinningResult();

    public String getName() {
        return name;
    }
}
