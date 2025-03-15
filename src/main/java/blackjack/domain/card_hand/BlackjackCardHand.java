package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class BlackjackCardHand {
    
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACK_JACK_SUM = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    
    private final CardHand cardHand;
    
    private BlackjackCardHand(final CardHand cardHand) {
        GlobalValidator.validateNotNull(BlackjackCardHand.class, cardHand);
        this.cardHand = cardHand;
    }
    
    public static BlackjackCardHand createWithInitialCards(final BlackjackCardHandInitializer initializer) {
        GlobalValidator.validateNotNull(BlackjackCardHand.class, initializer);
        final CardHand cardHand = new CardHand();
        cardHand.addCards(initializer.handoutInitialCards());
        return new BlackjackCardHand(cardHand);
    }
    
    public int getBlackjackSum() {
        List<Integer> sums = calculatePossibleSums();
        int minSum = findMinimumSum(sums);
        
        if (minSum > BUST_THRESHOLD) {
            return minSum;
        }
        return findClosestToBlackjack(sums);
    }
    
    public boolean isBust() {
        return getBlackjackSum() > BUST_THRESHOLD;
    }
    
    public boolean isBlackjack() {
        return getBlackjackSum() == BLACK_JACK_SUM && cardHand.getCardCount() == BLACK_JACK_CARD_COUNT;
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
    
    public int getCardCount() {
        return cardHand.getCardCount();
    }
    
    public boolean isAddedUpToMax() {
        return getBlackjackSum() == BLACK_JACK_SUM;
    }
}
