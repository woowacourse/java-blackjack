package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.util.GlobalValidator;

import java.util.List;

public abstract class AbstractBlackjackCardHand {
    
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACK_JACK_SUM = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    
    private final CardHand cardHand;
    
    protected AbstractBlackjackCardHand(final BlackjackCardHandInitializer initializer) {
        GlobalValidator.validateNotNull(initializer);
        final CardHand cardHand = new CardHand();
        cardHand.addCards(initializer.handoutInitialCards());
        this.cardHand = cardHand;
    }
    
    abstract List<Card> getInitialCards();
    
    /**
     * 카드를 한장 추가하는 기능입니다.
     * 만약 카드를 한장 추가하는 기능을 외부로 노출시켜야 하는 경우에는 public 으로 재정의하여 사용하세요.
     * @param card 추가할 카드
     */
    protected void addCard(Card card) {
        cardHand.addCard(card);
    }
    
    public int getBlackjackSum() {
        List<Integer> sums = calculatePossibleSums();
        int minSum = findMinimumSum(sums);
        
        if (minSum > BUST_THRESHOLD) {
            return minSum;
        }
        return findClosestToBlackjack(sums);
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
    
    public int getCardCount() {
        return cardHand.getCardCount();
    }
    
    public boolean isBust() {
        return getBlackjackSum() > BUST_THRESHOLD;
    }
    
    public boolean isAddedUpToMax() {
        return getBlackjackSum() == BLACK_JACK_SUM;
    }
    
    public boolean isBlackjack() {
        return getBlackjackSum() == BLACK_JACK_SUM && cardHand.getCardCount() == BLACK_JACK_CARD_COUNT;
    }
}
