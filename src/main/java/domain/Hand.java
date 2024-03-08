package domain;

import java.util.List;

import static domain.Player.BLACK_JACK;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getLetterValue)
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int countAceCard() {
        return (int) this.cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public int calculateScore() { //TODO: 이부분 의미 전달되게 코드 작성할 수 있지 않을까?
        int aceCount = countAceCard();
        int sum = calculateSum();
        //메소드 or 상수로 이름부여
        while (sum > BLACK_JACK && aceCount > 0) {
            aceCount--;
            sum -= 10;
        }

        return sum;
    }

    public List<Card> getCards() {
        return cards;
    }
}
