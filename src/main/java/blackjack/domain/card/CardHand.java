package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {
    
    private static final int MAXIMUM_THRESHOLD = 21;
    private static final int DIFF_OF_ACE_VALUES = 10;
    
    private final List<Card> cards;
    
    public CardHand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }
    
    public void add(Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public int sum() {
        int sum = cards.stream()
                       .mapToInt(Card::getRankValue)
                       .sum();
        
        if (hasAce() && canChangeAceValueToEleven(sum)) {
            sum += DIFF_OF_ACE_VALUES;
        }
        
        return sum;
    }
    
    private boolean hasAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }
    
    private boolean canChangeAceValueToEleven(int sum) {
        return sum + DIFF_OF_ACE_VALUES <= MAXIMUM_THRESHOLD;
    }
}
