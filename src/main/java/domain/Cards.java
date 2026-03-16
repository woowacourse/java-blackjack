package domain;

import java.util.ArrayList;
import java.util.List;

import domain.enums.CardRank;

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

    public List<Card> getCards() {
        return cards;
    }
}
