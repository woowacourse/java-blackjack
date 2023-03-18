package blackjack.domain.participant;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int MIN_ACE_VALUE = 1;
    private static final int MAX_ACE_VALUE = 11;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_COUNT = 2;

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        validateNull(card);
        cards.add(card);
    }

    private void validateNull(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("아무런 카드도 입력되지 않았습니다.");
        }
    }

    public int getCardCount() {
        return cards.size();
    }


    public List<String> getCardsValueAndPattern() {
        return cards.stream()
            .map(Card::getCardValueAndPattern)
            .collect(Collectors.toList());
    }

    public int calculateScore() {
        int commonSum = calculateStandardAndCourtCardScore();
        List<AceCard> aceCards = extractAceCards();
        int aceCardCount = aceCards.size();
        int aceSum = calculateAceCardScore(commonSum, aceCardCount);
        return commonSum + aceSum;
    }


    private int calculateStandardAndCourtCardScore() {
        return cards.stream()
                .filter((card) -> card.getValue() != MAX_ACE_VALUE)
                .mapToInt(Card::getValue)
                .sum();
    }

    private List<AceCard> extractAceCards() {
        return cards.stream()
                .filter((card) -> card.getValue() == MAX_ACE_VALUE)
                .map((card) -> (AceCard) card)
                .collect(Collectors.toList());
    }

    private int calculateAceCardScore(int commonSum, int aceCardCount) {
        int sum = 0;
        for (int restCount = aceCardCount; restCount > 0; restCount--) {
            int aceScore = decideAceScore(commonSum, restCount);
            commonSum += aceScore;
            sum += aceScore;
        }
        return sum;
    }

    private int decideAceScore(int sum, int restAceCount) {
        if (isAfford(sum, restAceCount)) {
            return MAX_ACE_VALUE;
        }
        return MIN_ACE_VALUE;
    }

    private boolean isAfford(int sum, int restAceCount) {
        final int ACE_VALUE_GAP = MAX_ACE_VALUE - MIN_ACE_VALUE;
        return (BLACKJACK_SCORE - sum) - restAceCount * MIN_ACE_VALUE >= ACE_VALUE_GAP;
    }

    public boolean isBlackjack() {
        return getCardCount() == BLACKJACK_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
