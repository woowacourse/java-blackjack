package domain.model;

import java.util.List;

import static constant.BlackJackConstant.BURST_CRITERIA;

public class Deck {

    private int sum = 0;
    private DeckStatus deckStatus;
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

    public List<Card> getCards() {
        return List.copyOf(cards);
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

    public void calculateSum() {
        sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public void append(Card card) {
        if (deckStatus == DeckStatus.ALIVE) {
            this.cards.add(card);
            calculateSum();
            checkStatus();
        }
    }

    // 최종 점수 계산 메서드
    public int calculateFinalSum() {
        boolean hasAce = cards.stream().anyMatch(Card::isAce);
        if (hasAce && sum <= 11) {
            return sum + 10;
        }
        return sum;
    }

    private static DeckStatus judgeDeckStatus(int sum) {
        if (sum > BURST_CRITERIA) {
            return DeckStatus.BURST;
        }
        return DeckStatus.ALIVE;
    }

    public boolean isBurst() {
        return deckStatus == DeckStatus.BURST;
    }

    public boolean isAlive() {
        return deckStatus == DeckStatus.ALIVE;
    }
}
