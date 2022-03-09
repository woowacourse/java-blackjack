package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
    public static final int BUST_STANDARD = 21;

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

    public String getName() {
        return name;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    private int cardSum() {
        return this.cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public Result checkResult(int otherScore) {
        return Result.check(cardSum(), otherScore);
    }
}
