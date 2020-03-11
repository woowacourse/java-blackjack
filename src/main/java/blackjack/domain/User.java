package blackjack.domain;

import blackjack.exception.UserNameEmptyException;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int ACE_CRITICAL_POINT = 11;
    private static final int ACE_UPPER_POINT = 11;
    private static final int ACE_LOWER_POINT = 1;

    private String name;
    private List<Card> cards = new ArrayList<>();

    public User(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException("유저의 이름은 공백일 수 없습니다.");
        }
        this.name = name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce() && score <= ACE_CRITICAL_POINT) {
            score += ACE_UPPER_POINT - ACE_LOWER_POINT;
        }
        return score;
    }

    private int calculateRawScore() {
        return cards.stream()
                    .mapToInt(Card::getPoint)
                    .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
