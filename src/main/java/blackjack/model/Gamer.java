package blackjack.model;

import java.util.ArrayList;
import java.util.List;
//TODO: getter 사용 관련 리팩터링
public class Gamer {

    private List<Card> cards = new ArrayList<>();
    private int sumOfCardNumber = 0;

    public void addCard(Card card) {
        validateDuplicatedCard(card);
        cards.add(card);
        sumNewCardNumber(card);
    }

    private void validateDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("[ERROR] 중복된 카드는 받을 수 없습니다.");
        }
    }

    private void sumNewCardNumber(Card card) {
        sumOfCardNumber +=card.extractCardNumber();
    }

    public List<Card> getCards() {
        return cards;
    }
}
