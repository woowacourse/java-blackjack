package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int ACE_ADDITIONAL_SCORE = 11;
    public static final int CARD_COUNT = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean isAceExist() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return getFinalScore() == Game.BLACKJACK_VALUE && cards.size() == CARD_COUNT;
    }

    public boolean isBust() {
        return getFinalScore() > Game.BLACKJACK_VALUE;
    }

    public int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.getCardRank().getNumber();
        }
        return total;
    }

    private int calculateAceScore() {
        if (!isAceExist() || calculateScore() > ACE_ADDITIONAL_SCORE) {
            return 0;
        }
        return ACE_ADDITIONAL_SCORE - 1;
    }

    public int getFinalScore() {
        return calculateScore() + calculateAceScore();
    }

    public Card getFirstCard() {
        return getCards().getFirst();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public List<String> getCardsDisplay() {
        List<String> cardsDisplay = new ArrayList<>();
        for (Card card : cards) {
            cardsDisplay.add(card.getCardDisplay());
        }
        return new ArrayList<>(cardsDisplay);
    }
}
