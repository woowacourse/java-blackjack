package blackjack.domain;

import blackjack.utils.Validator;

import java.util.List;

public class Player extends Human {

    private static final String EQUALS_DEALER_NAME_MESSAGE = "딜러와 동일한 이름은 사용할 수 없습니다.";
    private static final String RECEIVED_FLAG_MESSAGE = "y, n 중에서 입력해주세요.";
    private static final String GIVEN_SYMBOL = "y";
    private static final String NOT_GIVEN_SYMBOL = "n";

    private final String name;
    private final Cards cards = new Cards();

    public Player(final String name) {
        Validator.validateNullOrEmpty(name);
        validateEqualsDealerName(name);
        this.name = name;
    }

    public boolean answer(final String receivedFlag) {
        Validator.validateNullOrEmpty(receivedFlag);
        if (receivedFlag.equalsIgnoreCase(GIVEN_SYMBOL)) {
            return true;
        }
        if (receivedFlag.equalsIgnoreCase(NOT_GIVEN_SYMBOL)) {
            return false;
        }
        throw new IllegalArgumentException(RECEIVED_FLAG_MESSAGE);
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(Dealer.getName())) {
            throw new IllegalArgumentException(EQUALS_DEALER_NAME_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> getCards() {
        return cards.getAllCards();
    }

    @Override
    public int getTotal() {
        return cards.calculateTotal();
    }

    @Override
    public void dealInit(final List<Card> initCards) {
        cards.add(initCards);
    }

    @Override
    public void hit(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    @Override
    public boolean isPossibleToDraw() {
        return cards.isUnderBlackjack();
    }
}
