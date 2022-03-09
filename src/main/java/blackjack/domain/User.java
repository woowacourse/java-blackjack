package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";
    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFFERENCE = 10;

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
        return Result.check(cardSum(), otherScore);
    }

    private int cardSum() {
        int sum = this.cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if (sum > BUST_STANDARD) {
            sum = adjustSum(sum);
        }

        return sum;
    }

    private int adjustSum(int sum) {
        int aceCount = getAceCount();

        while (sum > BUST_STANDARD && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount -= 1;
        }
        return sum;
    }

    private int getAceCount() {
        return (int) this.cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE.getNumber())
                .count();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
