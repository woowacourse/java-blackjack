package blackjack.domain.card_hand;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;
import blackjack.domain.Player;
import blackjack.domain.WinningStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CardHand {
    
    private static final int BURST_THRESHOLD = 21;
    private static final int BLACK_JACK_SUM = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    
    protected final List<Card> cards = new ArrayList<>();
    
    public CardHand(final CardHandInitializer initializer) {
        cards.addAll(initializer.init());
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
    
    public WinningStatus isWin(final CardHand other) {
        if (checkBurstCase(other)) {
            return getBurstResult(other);
        }
        
        if (getSum() == other.getSum()) {
            return getDrawResult(other);
        }
        
        return getSum() > other.getSum() ? WinningStatus.승리 : WinningStatus.패배;
    }
    
    private boolean checkBurstCase(final CardHand other) {
        return isBurst() || other.isBurst();
    }
    
    private WinningStatus getBurstResult(final CardHand other) {
        if (isAllBurst(other)) {
            return WinningStatus.무승부;
        }
        
        if (isMyCardOnlyBurst(other)) {
            return WinningStatus.패배;
        }
        
        return WinningStatus.승리;
    }
    
    private WinningStatus getDrawResult(final CardHand other) {
        if (is21()) {
            return judgeDouble21(other);
        }
        return WinningStatus.무승부;
    }
    
    private WinningStatus judgeDouble21(final CardHand other) {
        if (isBlackjack() != other.isBlackjack()) {
            if (isBlackjack()) {
                return WinningStatus.승리;
            }
            return WinningStatus.패배;
        }
        return WinningStatus.무승부;
    }
    
    private boolean isMyCardOnlyBurst(final CardHand other) {
        return isBurst() && !other.isBurst();
    }
    
    private boolean isAllBurst(final CardHand other) {
        return isBurst() && other.isBurst();
    }
    
    public boolean isBurst() {
        return getSum() > BURST_THRESHOLD;
    }
    
    private boolean isBlackjack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && is21();
    }
    
    public boolean is21() {
        return getSum() == BLACK_JACK_SUM;
    }
}
