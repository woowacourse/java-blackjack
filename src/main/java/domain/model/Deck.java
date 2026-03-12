package domain.model;

import java.util.List;

import static constant.BlackJackConstant.BURST_CRITERIA;

public class Deck {

    private DeckStatus deckStatus = DeckStatus.ALIVE;
    private List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck of(List<Card> cards) {
        Deck deck = new Deck(cards);
        deck.checkStatus(deck.calculateFinalSum());
        return deck;
    }

    public int getSum() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public DeckStatus getDeckStatus() {
        return deckStatus;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    // 최종 점수 계산 메서드
    public int calculateFinalSum() {
        // 카드를 순회하며 A가 있는지 확인
        int sum = getSum();

        boolean hasAce = cards.stream().anyMatch(Card::isAce);

        if (hasAce && sum <= 11) {
            // A가 있으면 11로 계산해서 21이 넘는지 확인
            // 넘지 않으면 11로 처리
            sum += 10;
        }
        return sum;
        // 만약 넘으면 sum은 그대로
    }

    public void append(Card card) {
        if (deckStatus == DeckStatus.ALIVE) {
            this.cards.add(card);
            checkStatus(calculateFinalSum());
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card getLastCard() {
        return cards.getLast();
    }

    private void checkStatus(int sum) {
        if (sum > BURST_CRITERIA) {
            deckStatus = DeckStatus.BURST;
        }
        if (sum == 21) {
            deckStatus = DeckStatus.BLACK_JACK;
        }
    }
}
