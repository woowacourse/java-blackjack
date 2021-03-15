package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public class CardHand {
    
    private static final int ELEVEN_OF_ACE_VALUE = 11;
    private static final int ONE_OF_ACE_VALUE = 1;
    
    private static final int BLACKJACK_HAND_SIZE = 2;
    
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
        
        if (hasAce()) {
            sum -= ONE_OF_ACE_VALUE;
            sum = addAceValue(sum);
        }
        return sum;
    }
    
    private int addAceValue(int sum) {
        if (isBustIfAddEleven(sum)) {
            return sum + ONE_OF_ACE_VALUE;
        }
        return sum + ELEVEN_OF_ACE_VALUE;
    }
    
    private boolean isBustIfAddEleven(int sum) {
        return sum + ELEVEN_OF_ACE_VALUE > BLACKJACK_SCORE;
    }
    
    private boolean hasAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }
    
    public Card get(int cardIndex) {
        return cards.get(cardIndex);
    }
    
    public boolean isBlackjack() {
        return (sum() == BLACKJACK_SCORE) && (cards.size() == BLACKJACK_HAND_SIZE);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardHand cardHand = (CardHand) o;
        return Objects.equals(cards, cardHand.cards);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
