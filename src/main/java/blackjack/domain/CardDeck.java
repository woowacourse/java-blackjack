package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private static final int MIN_ACE_VALUE = 1;
    private static final int MAX_ACE_VALUE = 11;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BURST_CODE = -1;

    private final List<Card> cards;

    public CardDeck() {
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


    public List<String> getCardsInfo() {
        return cards.stream()
            .map(Card::getCardInfo)
            .collect(Collectors.toList());
    }

    public int calculateScore(CardDeck deck) {
        int commonSum = calculateStandardAndCourtCardScore(deck);
        List<AceCard> aceCards = extractAceCards(deck);
        int aceCardCount = aceCards.size();
        int aceSum = calculateAceCardScore(commonSum, aceCardCount);
        return commonSum + aceSum;
    }


    private int calculateStandardAndCourtCardScore(CardDeck deck) {
        return deck.getCards().stream()
                .filter((card) -> card.getValue() != MAX_ACE_VALUE)
                .mapToInt(Card::getValue)
                .sum();
    }

    private List<AceCard> extractAceCards(CardDeck deck) {
        return deck.getCards().stream()
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

    public List<Card> getCards() {
        return cards;
    }
}
