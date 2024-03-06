package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

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
                    .filter(AceCard.class::isInstance)
                    .map(AceCard.class::cast)
                    .filter(AceCard::isSoftAce)
                    .findFirst()
                    .ifPresent(AceCard::changeToHardAce);
        }
    }

    public boolean isBust() {
        return calculateHand() > 21;
    }

    public int calculateHand() {
        return cards.stream()
                .map(Card::getCardActualValue)
                .reduce(Integer::sum)
                .orElse(0);
    }


    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
