package domain.member;

import domain.card.Card;
import domain.card.CardNumber;
import constant.exception.DuplicatedException;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        cards = List.of();
    }

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getAllCard() {
        return List.copyOf(cards);
    }

    public Hand appendCard(Card card) {
        validateDuplicate(card);
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public int calculateTotalValue() {
        int aceAmount = aceAmount();
        if (aceAmount > 0) {
            return softHandAces(aceAmount);
        }
        return cards.stream()
                .mapToInt(Card::number)
                .sum();
    }

    private int softHandAces(int aceAmount) {
        int sumWithOutAce = cards.stream()
                .filter(card -> card.number() != CardNumber.ACE.getValue())
                .mapToInt(Card::number)
                .sum();
        if (sumWithOutAce >= CardNumber.ACE.getValue()) {
            return sumWithOutAce + aceAmount;
        }
        return sumWithOutAce + CardNumber.ACE.getValue() + aceAmount - 1;
    }

    private int aceAmount() {
        return Math.toIntExact(
                cards.stream()
                        .filter(card -> card.number() == CardNumber.ACE.getValue())
                        .count()
        );
    }

    private void validateDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new DuplicatedException();
        }
    }
}
