package blackjack.model;

import java.util.ArrayList;
import java.util.List;

//TODO: getter 사용 관련 리팩터링
public class Gamer {

    private final List<Card> cards = new ArrayList<>();
    private int sumOfCardNumber = 0;

    public void addCard(Card card) {
        validateDuplicatedCard(card);
        cards.add(card);
        sumNewCardNumber(card);
    }

    public boolean isPossibleToAddCard(int number) {
        return sumOfCardNumber < number;
    }
    
    //TODO : 메서드 분리
    public int calculateFinalScore() {
        int sum = 0;

        if (cards.stream()
                .anyMatch(Card::isAce)) {
            sum = cards.stream()
                    .mapToInt(Card::extractCardNumber)
                    .sum();

            if (sum + 10 > 21) {
                return sum;
            }

            return sum + 10;
        }

        sum = cards.stream()
                .mapToInt(Card::extractCardNumber)
                .sum();

        return sum;
    }

    private void sumNewCardNumber(Card card) {
        sumOfCardNumber += card.extractCardNumber();
    }

    private void validateDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("[ERROR] 중복된 카드는 받을 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
