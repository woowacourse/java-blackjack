package blackjack.domain;

import com.sun.source.tree.NewArrayTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {
    
    public static final int BURST_THRESHOLD = 21;
    private final List<Card> cards = new ArrayList<>();
    
    public void addCard(final Card newCard) {
        cards.add(newCard);
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public int getSum() {
        final List<List<Integer>> availableNumbers = cards.stream()
                .map(Card::getBlackjackNumber)
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
