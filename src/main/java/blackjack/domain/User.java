package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.PointCalculator.cardSum;

public class User {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
    private static final int BUST_STANDARD = 21;

    private final String name;
    private final List<Card> cards;

    public User(String name) {
        validateName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public Result checkResult(int otherScore) {
        return Result.check(cardSum(cards), otherScore);
    }

    public boolean checkBust() {
        return cardSum(cards) > BUST_STANDARD;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
