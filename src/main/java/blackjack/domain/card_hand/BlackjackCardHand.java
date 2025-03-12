package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;

import java.util.ArrayList;
import java.util.List;

public final class BlackjackCardHand {
    
    private static final int BUST_THRESHOLD = 21;
    
    private final CardHand cardHand;
    
    public BlackjackCardHand(final BlackjackCardHandInitializer initializer) {
        this.cardHand = new CardHand();
        this.cardHand.addCards(initializer.handoutInitialCards());
    }
    
    public int getBlackjackSum() {
        final List<List<Integer>> availableNumbers = cardHand.getCards().stream()
                .map(Card::getBlackjackValue)
                .toList();
        
        List<Integer> availableSum = createAvailableSum(availableNumbers);
        int minAvailableSum = calculateMinAvailableSum(availableSum);
        
        if (minAvailableSum > BUST_THRESHOLD) {
            return minAvailableSum;
        }
        return calculateClosestToBlackJack(availableSum);
    }
    
    private List<Integer> createAvailableSum(final List<List<Integer>> availableNumbers) {
        List<Integer> availableSum = new ArrayList<>();
        availableSum.add(0);
        
        for (List<Integer> availableNumber : availableNumbers) {
            availableSum = calculateAvailableSum(availableNumber, availableSum);
        }
        return availableSum;
    }
    
    private static Integer calculateMinAvailableSum(final List<Integer> availableSum) {
        return availableSum.stream()
                .min(Integer::compareTo)
                .orElseThrow();
    }
    
    private Integer calculateClosestToBlackJack(final List<Integer> availableSum) {
        return availableSum.stream()
                .filter(number -> number <= BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseThrow();
    }
    
    private List<Integer> calculateAvailableSum(final List<Integer> availableNumber, List<Integer> availableSum) {
        return availableSum.stream()
                .flatMap(currentSum -> availableNumber.stream().map(number -> number + currentSum))
                .toList();
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    public void addCard(Card card) {
        cardHand.addCard(card);
    }
}
