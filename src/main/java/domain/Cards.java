package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int ACE_ADDITIONAL_SCORE = 10;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

//    public void shuffle() {
//        Collections.shuffle(cards);
//    }
    public void addCard(Card card) {
        this.cards.add(card);
    }

//    public Card pop() {
//        return cards.removeFirst();
//
//        // 리스트 비었을 때 처리 필요
//    }

    public boolean isAceExist() {
        return cards.stream()
                .anyMatch(c -> c.getCardRank().equals(CardRank.ACE));
    }

    public boolean isBlackjack() {
        return calculateScore() == Game.BLACKJACK_VALUE && cards.size() == Game.CARD_COUNT;
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
