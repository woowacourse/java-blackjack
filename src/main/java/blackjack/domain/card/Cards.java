package blackjack.domain.card;

import static blackjack.domain.participant.Participant.ACE_ADDITIONAL_NUMBER;
import static blackjack.domain.participant.Participant.BUST_THRESHOLD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";

    private final List<Card> cardHand;

    public Cards(List<Card> cardHand) {
        validateDuplicate(cardHand);
        this.cardHand = new ArrayList<>(cardHand);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public void concat(Cards cards) {
        cardHand.addAll(cards.getCardHand());
        new Cards(cardHand);
    }

    public int getLowestSum() {
        return cardHand.stream()
                .map(Card::getNumber)
                .map(Number::getScore)
                .reduce(0, Integer::sum);
    }

    public int getBestPossible(int sum) {
        for (Card card : cardHand) {
            if (card.isAce() && sum + ACE_ADDITIONAL_NUMBER <= BUST_THRESHOLD) {
                sum += ACE_ADDITIONAL_NUMBER;
            }
        }
        return sum;
    }

    public int getHighestSum() {
        int sum = cardHand.stream()
                .map(Card::getNumber)
                .map(number -> {
                    if (number == Number.ACE) {
                        return number.getScore() + ACE_ADDITIONAL_NUMBER;
                    }
                    return number.getScore();
                })
                .reduce(0, Integer::sum);

        if (sum > BUST_THRESHOLD) {
            return getLowestSum();
        }
        return sum;
    }

    public List<Card> getCardHand() {
        return cardHand;
    }
}
