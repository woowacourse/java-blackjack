package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;

import java.util.List;

public final class BlackjackCardHand implements BlackjackWinDeterminable {
    
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACK_JACK_MAX_SUM = 21;
    
    private final CardHand cardHand;
    
    public BlackjackCardHand(final BlackjackCardHandInitializer initializer) {
        this.cardHand = new CardHand();
        this.cardHand.addCards(initializer.handoutInitialCards());
    }
    
    @Override
    public int getBlackjackSum() {
        List<Integer> sums = calculatePossibleSums();
        int minSum = findMinimumSum(sums);
        
        if (minSum > BUST_THRESHOLD) {
            return minSum;
        }
        return findClosestToBlackjack(sums);
    }
    
    @Override
    public boolean isBust() {
        return getBlackjackSum() > BLACK_JACK_MAX_SUM;
    }
    
    @Override
    public boolean isBlackjack() {
        return getBlackjackSum() == BLACK_JACK_MAX_SUM && cardHand.getCards().size() == 2;
    }
    
    private List<Integer> calculatePossibleSums() {
        return cardHand.getCards().stream()
                .map(Card::getBlackjackValue)
                .reduce(
                        List.of(0),
                        this::addCardValues
                );
    }
    
    private List<Integer> addCardValues(List<Integer> currentSums, List<Integer> cardValues) {
        return currentSums.stream()
                .flatMap(currentSum -> cardValues.stream()
                        .map(value -> currentSum + value))
                .distinct()
                .toList();
    }
    
    private int findMinimumSum(List<Integer> sums) {
        return sums.stream()
                .min(Integer::compareTo)
                .orElseThrow(IllegalStateException::new);
    }
    
    private int findClosestToBlackjack(List<Integer> sums) {
        return sums.stream()
                .filter(sum -> sum <= BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseThrow(IllegalStateException::new);
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    public void addCard(Card card) {
        cardHand.addCard(card);
    }
}
