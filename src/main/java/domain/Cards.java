package domain;

import domain.generator.CardNumberGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int MAXINUM_SUM_OF_CARD = 21;
    private static final int TREAT_A_AS_ONE = 10;
    private static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateDuplicated(cards);
        this.cards = cards;
    }

    public int sumOfCards() {
        int sum = calculateSum();
        return checkOver21AndContainsA(sum);
    }

    private int calculateSum() {
        int sum = 0;
        Card card = cards.get(0);

        for (int i = 1; i < cards.size(); i ++) {
            sum = card.sum(cards.get(i));
            card = new Card("TEMP",sum);
        }
        return sum;
    }

    private int checkOver21AndContainsA(final int sum) {
        if (sum > MAXINUM_SUM_OF_CARD && containsA()) {
            return sum - TREAT_A_AS_ONE;
        }
        return sum;
    }

    private boolean containsA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public boolean addCard(final Card otherCard) {
        if (isNotContains(otherCard)) {
            cards.add(otherCard);
            return true;
        }
        return false;
    }

    private boolean isNotContains(final Card otherCard) {
        return !cards.contains(otherCard);
    }

    private void validateDuplicated(final List<Card> cards) {
        int size = new HashSet<>(cards).size();
        if (cards.size() != size) {
            int duplicatedCardsNum = cards.size() - size + 1;
            throw new IllegalArgumentException(duplicatedCardsNum + "개의 카드가 중복됩니다");
        }
    }

    public static Cards pickInitialCards(CardNumberGenerator cardNumberGenerator){
        List<Card> pickedCards = new ArrayList<>();
        for (int i =0 ;i<INITIAL_CARD_SIZE;i++){
            pickedCards.add(CardBox.get(cardNumberGenerator.generateIndex()));
        }
        return new Cards(pickedCards);
    }

    public boolean isMaximumNumber() {
        return sumOfCards() == MAXINUM_SUM_OF_CARD;
    }

    public int sizeOfCards(){
        return cards.size();
    }

    public List<String> copyCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }
}
