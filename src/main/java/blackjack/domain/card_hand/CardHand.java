package blackjack.domain.card_hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

public class CardHand {
    
    private static final int BURST_THRESHOLD = 21;
    
    protected final List<Card> cards = new ArrayList<>();
    
    public CardHand(final CardHandInitializer initializer) {
        cards.addAll(initializer.init());
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public int getSum() {
        final List<List<Integer>> availableNumbers = cards.stream()
                .map(Card::getBlackjackValue)
                .toList();
        
        List<Integer> availableSum = createAvailableSum(availableNumbers);
        int minNumber = calculateMinAvailableSum(availableSum);
        
        if (minNumber > BURST_THRESHOLD) {
            return minNumber;
        }
        return calculateClosestToBlackJack(availableSum);
    }
    
    private static Integer calculateMinAvailableSum(final List<Integer> availableSum) {
        return availableSum.stream()
                .min(Integer::compareTo)
                .orElseThrow();
    }
    
    private List<Integer> createAvailableSum(final List<List<Integer>> availableNumbers) {
        List<Integer> availableSum = new ArrayList<>();
        availableSum.add(0);
        
        for (List<Integer> availableNumber : availableNumbers) {
            availableSum = calculateAvailableSum(availableNumber, availableSum);
        }
        return availableSum;
    }
    
    private Integer calculateClosestToBlackJack(final List<Integer> availableSum) {
        return availableSum.stream()
                .filter(number -> number <= BURST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseThrow();
    }
    
    private List<Integer> calculateAvailableSum(final List<Integer> availableNumber, List<Integer> availableSum) {
        final ArrayList<Integer> newList = new ArrayList<>();
        for (Integer currentSum : availableSum) {
            addAvailableSums(availableNumber, currentSum, newList);
        }
        return newList;
    }
    
    private void addAvailableSums(final List<Integer> availableNumber, final Integer currentSum, final ArrayList<Integer> newList) {
        for (Integer number : availableNumber) {
            newList.add(currentSum + number);
        }
    }
}
