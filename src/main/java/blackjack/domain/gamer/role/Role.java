package blackjack.domain.gamer.role;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

public abstract class Role {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 최소 1글자, 최대 6글자로 입력해야 합니다.";
    private static final String NAME_EMPTY_ERROR_MESSAGE = "빈 문자는 이름으로 입력할 수 없습니다.";
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 6;

    protected final CardGroup cardGroup = new CardGroup();
    private final String name;

    public Role(String name) {
        validateName(name);
        this.name = name;
    }

    public abstract boolean isAddable();

    public abstract void addCard(Card card);

    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateLength(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    public void openAllCards() {
        cardGroup.open();
    }

    public boolean isBust() {
        return cardGroup.isBust();
    }

    public int getScore() {
        return cardGroup.getScore();
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public String getName() {
        return this.name;
    }

    public int getCardsSize() {
        return cardGroup.getSize();
    }
}
