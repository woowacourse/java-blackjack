package blackjack.domain;

import blackjack.utils.Validator;

import java.util.List;

public class Player extends Human {

    public static final String EQUALS_DEALER_NAME_MESSAGE = "딜러와 동일한 이름은 사용할 수 없습니다.";
    public static final String RECEIVING_ANSWER_MESSAGE = "y, n 중에서 입력해주세요.";
    private static final String RECEIVE_SYMBOL = "y";
    private static final String NOT_RECEIVE_SYMBOL = "n";

    private final String name;
    private final Cards cards = new Cards();

    public Player(final String name) {
        Validator.validateNullOrEmpty(name);
        validateEqualsDealerName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean answer(final String answer) {
        Validator.validateNullOrEmpty(answer);
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
    public List<String> showCards() {
        return cards.getAllCards();
    }

    @Override
    public int showSumOfCards() {
        return cards.calculateTotal();
    }

    @Override
    public void receiveInitCards(final List<Card> initCards) {
        cards.add(initCards);
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    @Override
    public boolean isReceived() {
        return cards.isUnderBlackjack();
    }
}
