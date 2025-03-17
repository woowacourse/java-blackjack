package domain;

import static domain.CardNumberType.determineAceNumber;
import static domain.GameResultStatus.BLACKJACK;
import static domain.GameResultStatus.DRAW;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand createEmpty() {
        return new Hand(new ArrayList<>());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean isBust() {
        int sumWithLowAce = cards.stream()
                .map(Card::cardNumberType)
                .mapToInt(CardNumberType::getDefaultNumber)
                .sum();
        return sumWithLowAce > BLACKJACK_NUMBER;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addAll(List<Card> receiveCards) {
        cards.addAll(receiveCards);
    }

    public int calculateSum() {
        int cardsSumWithoutAce = calculateSumWithoutAce();
        return calculateSumWithAces(cardsSumWithoutAce);
    }

    public GameResultStatus calculateGameResultStatus(Hand dealerHand) {
        if (isBlackjack() && dealerHand.isBlackjack()) {
            return DRAW;
        }
        if (isBlackjack()) {
            return BLACKJACK;
        }
        if (isBust() || dealerHand.isBlackjack()) {
            return LOSE;
        }
        if (dealerHand.isBust()) {
            return WIN;
        }
        return compareCardsSum(dealerHand);
    }

    private boolean isBlackjack() {
        return this.cards.size() == BLACKJACK_CARD_COUNT && calculateSum() == BLACKJACK_NUMBER;
    }

    private GameResultStatus compareCardsSum(Hand dealerHand) {
        if (isLargerThan(dealerHand)) {
            return WIN;
        }
        if (dealerHand.isLargerThan(this)) {
            return LOSE;
        }
        return DRAW;
    }

    private boolean isLargerThan(Hand dealerHand) {
        return this.calculateSum() > dealerHand.calculateSum();
    }

    private int calculateSumWithoutAce() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::getDefaultNumber)
                .sum();
    }

    private int calculateSumWithAces(int cardsSumWithoutAce) {
        int result = cardsSumWithoutAce;
        int aceCount = calculateAceCount();
        for (int count = 0; count < aceCount; count++) {
            result += determineAceNumber(result);
        }
        return result;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Hand other = (Hand) object;
        return Objects.equals(cards, other.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
