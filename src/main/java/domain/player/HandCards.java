package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private static final int MAX_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // 손에 쥔 카드 출력
    public List<String> cardsToString() {
        List<String> cardList = new ArrayList<>();

        for (Card card : cards) {
            cardList.add(card.toString());
        }

        return cardList;
    }

    // 손에 쥔 카드 점수 계산해 반환
    public int getCardScoreSum() {
        int sum = 0;

        for(Card card : cards) {
            sum += card.getValue();
        }

        if (canAce11(sum)) {
            return sum + ACE_BONUS_SCORE;
        }
        return sum;
    }

    private boolean canAce11(int sum) {
        return containsAce() && sum + ACE_BONUS_SCORE <= MAX_SCORE;
    }

    private boolean containsAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }

        return false;
    }
}
