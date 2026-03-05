package model;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    public static final int BUST_THRESHOLD = 21;
    private String name;
    private List<Card> hands;
    private boolean isDealer;

    private Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<Card>();
    }

    private Participant(String name, boolean isDealer) {
        this.name = name;
        this.hands = new ArrayList<Card>();
        this.isDealer = true;
    }

    public static Participant of(String input) {
        return new Participant(input);
    }

    public static Participant of(String input, boolean isDealer) {
        return new Participant(input, isDealer);
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public String getName() {
        return name;
    }

    public List<Card> draw(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }

    public List<String> open(int round) {
        if (isDealer && round == 1) {
            return List.of(hands.getFirst().toString());
        }

        return hands.stream()
                .map(Card::toString)
                .toList();
    }

    public int calculateScore() {
        // Ace가 있을 때는 점수에 반영을 하는데, 나에게 유리하게 총 점수를 확인하고 체크한다.(1 or 10point)
        int total = calculate(); // Ace를 11로 처리한 값.
        int aceCardCount = aceCount();
        while (aceCardCount-- > 0 && total > 21) {
            total -= 10;
        }

        return total;
    }

    public boolean isBust() {
        if (calculateScore() > BUST_THRESHOLD) {
            return true;
        }

        return false;
    }

    private int calculate() {
        return hands.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::toValue)
                .sum();
    }

    private int aceCount() {
        int count = 0;
        for (Card card : hands) {
            if (card.getCardNumber() == CardNumber.ACE) {
                count++;
            }
        }

        return count;
    }
}
