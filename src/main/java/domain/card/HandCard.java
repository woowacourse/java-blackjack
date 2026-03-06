package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCard {
    private static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int ACE_MAX_VALUE = 11;
    private static final int ACE_MIN_VALUE = 1;
    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public int cardCalculator() {
        int nonAceTotal = cards.stream().map(Card::getRankScore).filter(e -> e != 1).mapToInt(Integer::intValue).sum();
        int aceCount = (int) cards.stream().map(Card::getRankScore).filter(e -> e == 1).count();

        return aceCalculator(nonAceTotal, aceCount);
    }

    //이 메소드는 전체 점수를 리턴해서 이름이 맞지 않다. 아래 주석 메소드를 사용하는게 맞는가?
    private int aceCalculator(int nonAceTotal, int aceCount) {
        int totalSum = nonAceTotal + (aceCount * ACE_MAX_VALUE);
        int remainingAce = aceCount;
        while (totalSum > BLACKJACK_MAX_LIMIT && remainingAce > 0) {
            totalSum -= (ACE_MAX_VALUE - ACE_MIN_VALUE);
            remainingAce--;
        }
        return totalSum;
    }
    /*
    private int aceCalculator(int sum, int ace_cnt){
        return Math.min(ace_cnt, Math.max(0, (BLACKJACK_MAX_LIMIT - sum) / (ACE_MAX_VALUE - ACE_MIN_VALUE)));
    }
     */

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> getCardInfos() {
        return cards.stream().map(Card::getCardInfo).toList();
    }

    public String getFirstCardInfo() {
        return cards.getFirst().getCardInfo();
    }
}
