package blackjack.domain;

public class Player extends Human {

    private static final String EQUALS_DEALER_NAME_MESSAGE = "딜러와 동일한 이름은 사용할 수 없습니다.";
    private static final String RECEIVING_ANSWER_MESSAGE = "y, n 중에서 입력해주세요.";
    private static final String RECEIVE_SYMBOL = "y";
    private static final String NOT_RECEIVE_SYMBOL = "n";

    private final String name;

    public Player(final String name) {
        validateEqualsDealerName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean answer(final String answer) {
        if (answer.equalsIgnoreCase(RECEIVE_SYMBOL)) {
            return true;
        }
        if (answer.equalsIgnoreCase(NOT_RECEIVE_SYMBOL)) {
            return false;
        }
        throw new IllegalArgumentException(RECEIVING_ANSWER_MESSAGE);
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(Dealer.getName())) {
            throw new IllegalArgumentException(EQUALS_DEALER_NAME_MESSAGE);
        }
    }

    @Override
    public boolean isReceived() {
        return cards.isUnderBlackjack();
    }
}
