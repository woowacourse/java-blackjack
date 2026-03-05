package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final Integer ACE_MIN_VALUE=1;
    private static final Integer BLACKJACK_LIMIT_VALUE=21;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    //버스트면 true
    public boolean isBust() {
        return calculateTotalScore() > 21;
    }

    public Integer getHandSize() {
        return hand.size();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public Integer calculateTotalScore() {
        Integer sum = hand.stream()
                .filter(card-> card.getRank() != Rank.ACE)
                .mapToInt(card -> card.getCardScore())
                .sum();

        Integer aCount = (int) hand.stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .count();

        return aceCalculate(sum, aCount);
    }

    private Integer aceCalculate(Integer sum, Integer aCount){
        Integer totalSum = sum + (aCount * Rank.ACE.getValue());
        while(aCount > 0 && totalSum > BLACKJACK_LIMIT_VALUE){
            totalSum -= (Rank.ACE.getValue()- ACE_MIN_VALUE);
            aCount--;
        }
        return totalSum;
    }
}
