package domain.card;

import domain.CardCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int STANDARD_ACE_ELEVEN = 21;
    private static final String NO_CARDS = "입력한 카드가 없습니다.";

    private List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(Card... cards) {
        if (cards == null || cards.length == 0) {
            throw new NullPointerException(NO_CARDS);
        }

        List<Card> cardList = new ArrayList<>();
        Collections.addAll(cardList, cards);
        return new Cards(cardList);
    }

    public static Cards of(List<Card> cards) {
        if (cards == null || cards.size() == 0) {
            throw new NullPointerException(NO_CARDS);
        }

        return new Cards(cards);
    }

    private void validateNullCards() {
        if (this.cards == null || this.cards.isEmpty()) {
            throw new NullPointerException(NO_CARDS);
        }
    }

    public boolean isCardsSumUnderSixteen() {
        validateNullCards();
        return CardCalculator.calculateAceStrategy(this) <= DEALER_STANDARD_ADDITIONAL_CARD;
    }

    public boolean containAce() {
        validateNullCards();
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException(NO_CARDS);
        }

        this.cards.add(card);
    }

    public int getCardsSum() {
        return CardCalculator.calculateAceStrategy(this);
    }

    public boolean isBlackJack() {
        return containAce()
                && this.cards.size() == BLACK_JACK_SIZE
                && CardCalculator.calculateAceStrategy(this) == STANDARD_ACE_ELEVEN;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
