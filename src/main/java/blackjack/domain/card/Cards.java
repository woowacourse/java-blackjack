package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    // TODO: 가변리스트로 바꿀지 고민하기
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int sum() {
        int sumWithoutAce = cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(card -> card.number().getNumber())
            .sum();
        int sumOfAce = getSumOfAce(sumWithoutAce);
        return sumWithoutAce + sumOfAce;
    }

    // LAST TODO : 리팩토링 고민해보기
    private int getSumOfAce(int sumWithoutAce) {
        int sumOfAce = 0;
        int aceCardCount = getAceCardCount();
        if (aceCardCount >= 2) {
            sumOfAce += aceCardCount - 1;
        }
        if (aceCardCount >= 1) {
            sumOfAce += aceNumber(sumWithoutAce);
        }
        return sumOfAce;
    }

    private int getAceCardCount() {
        return (int)cards.stream()
            .filter(Card::isAce)
            .count();
    }

    // TODO: 상수화
    private int aceNumber(int sum) {
        if (sum + 11 > 21) {
            return 1;
        }
        return 11;
    }

    // TODO: 상수화
    public boolean isBlackjack() {
        if (cards.size() != 2) {
            return false;
        }
        return sum() == 21;
    }

    public int count() {
        return cards.size();
    }
}

