package domain.model;

import java.util.List;

import static constant.BlackJackConstant.BURST_CRITERIA;

public class Deck {

    private int sum = 0;
    private DeckStatus deckStatus = DeckStatus.ALIVE;
    private List<Card> cards;

    private Deck(List<Card> cards, int sum, DeckStatus deckStatus) {
        this.cards = cards;
        this.sum = sum;
        this.deckStatus = deckStatus;
    }

    public static Deck of(List<Card> cards) {
        int cardSum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        DeckStatus deckStatus1 = judgeDeckStatus(cardSum);
        return new Deck(cards, cardSum, deckStatus1);
    }

    public int getSum() {
        return sum;
    }

    public DeckStatus getDeckStatus() {
        return deckStatus;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void calculateSum() {
        sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    // 최종 점수 계산 메서드
    public void calculateFinalSum() {
        // 카드를 순회하며 A가 있는지 확인
        boolean hasAce = cards.stream().anyMatch(Card::isAce);
        if (hasAce && sum <= 11) {
            // A가 있으면 11로 계산해서 21이 넘는지 확인
            // 넘지 않으면 11로 처리
            sum += 10;
        }
        // 만약 넘으면 sum은 그대로
    }

    public void append(Card card) {
        if (deckStatus == DeckStatus.ALIVE) {
            this.cards.add(card);
            calculateSum();
            checkStatus();
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card getLastCard() {
        return cards.getLast();
    }

    public void checkStatus() {
        if (sum > BURST_CRITERIA) {
            deckStatus = DeckStatus.BURST;
        }
    }

    private static DeckStatus judgeDeckStatus(int sum) {
        if (sum > BURST_CRITERIA) {
            return DeckStatus.BURST;
        }
        return DeckStatus.ALIVE;
    }
}
