package domain.player;

import domain.card.Card;
import domain.card.TrumpNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards {
    private static final int MAX_SCORE = 21;

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

        return Collections.unmodifiableList(cardList);
    }

    // 손에 쥔 카드 점수 계산해 반환
    public int getCardScoreSum() {
        int sum = 0;
        int aceCount = 0;
        for(Card card : cards) {
            if (card.isAce()) {
                aceCount++;
                continue;
            }
            sum += card.getValue();
        }

        for (int i = 0; i < aceCount; i++) {
            sum += calculateAce(sum);
        }
        return sum;
    }

    public int quantity() {
        return cards.size();
    }

    private int calculateAce(int sum) {
        if (canUpGradeAce(sum)) {
            return TrumpNumber.ACE.getUpgradeValue();
        }

        return TrumpNumber.ACE.getValue();
    }

    private boolean canUpGradeAce(int sum) {
        return containsAce() && sum + TrumpNumber.ACE.getUpgradeValue() <= MAX_SCORE;
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
