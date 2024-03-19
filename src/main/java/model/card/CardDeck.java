package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    public static final int HIT_THRESHOLD = 21;
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
        changeSoftAceToHardWhenBust();
    }

    private void changeSoftAceToHardWhenBust() {
        if (isBust()) {
            cards.stream()
                    .filter(Card::isSoftAce)
                    .findFirst()
                    .ifPresent(Card::changeToHardAce);
        }
    }

    public boolean isBust() {
        return calculateHand() > HIT_THRESHOLD;
    }

    public int calculateHand() {
        return cards.stream()
                .map(Card::getCardActualValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateHand() == HIT_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
