package domain.model;

import java.util.List;

public class Deck {

    private DeckStatus deckStatus;
    private List<Card> cards;

    public static final int BLACKJACK_SCORE = 21;

    private Deck(List<Card> cards, DeckStatus deckStatus) {
        this.cards = cards;
        this.deckStatus = deckStatus;
    }

    public static Deck of(List<Card> cards) {
        Deck deck = new Deck(cards, DeckStatus.ALIVE);
        deck.judgeDeckStatus();
        return deck;
    }

    // 최종 점수 계산 메서드
    public int calculateFinalSum() {
        int sum = getSumWithAceAdditionalValue();
        int aceCount = getAceCount();
        while (sum > BLACKJACK_SCORE && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public DeckStatus getDeckStatus() {
        return deckStatus;
    }

    public int getSize() {
        return cards.size();
    }

    public Card getLastCard() {
        return cards.getLast();
    }

    public void append(Card card) {
        if (deckStatus == DeckStatus.ALIVE) {
            this.cards.add(card);
            judgeDeckStatus();
        }
    }

    private void judgeDeckStatus() {
        if (getSize() == 2 && isHasAce() && getSumWithAceAdditionalValue() == BLACKJACK_SCORE) {
            deckStatus = DeckStatus.BLACKJACK;
        }

        if (getSumWithAceDefaultValue() > BLACKJACK_SCORE) {
            deckStatus = DeckStatus.BURST;
        }
    }

    public boolean isBurst() {
        return deckStatus == DeckStatus.BURST;
    }

    public boolean isAlive() {
        return deckStatus == DeckStatus.ALIVE;
    }

    public boolean isBlackJack() {
        return deckStatus == DeckStatus.BLACKJACK;
    }

    public int getSumWithAceDefaultValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public int getSumWithAceAdditionalValue() {
        return cards.stream()
                .mapToInt(Card::getAdditionalValue)
                .sum();
    }

    private boolean isHasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
