package domain.model;

import java.util.List;

import static constant.BlackJackConstant.BURST_CRITERIA;

public class Deck {

    private int sum = 0;
    private DeckStatus deckStatus = DeckStatus.ALIVE;
    private List<Card> cards;

    private Deck(List<Card> deck) {
        this.cards = deck;
    }

    public static Deck of(List<Card> cards) {
        return new Deck(cards);
    }

    public int getSum() {
        return sum;
    }

    public void calculateSum() {
        sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    // 최종 점수 계산 메서드
    public void calculateFinalSum() {

        // 카드를 순회하며 A가 있는지 확인


        // A가 있으면 11로 계산해서 21이 넘는지 확인


        // 만약 넘으면 1로


        // 넘지 않으면 11로 처리

    }

    public void append(Card card) {
        if (deckStatus.equals(DeckStatus.ALIVE)) {
            this.cards.add(card);
            calculateSum();
        }
        checkStatus();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getLastCard() {
        return cards.getLast();
    }

    private void checkStatus() {
        if (sum > BURST_CRITERIA) {
            deckStatus = DeckStatus.BURST;
        }
    }
}
