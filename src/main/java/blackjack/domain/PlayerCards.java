package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCards {

    private static final int BUST_LIMIT = 22;
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public int getCardCount() {
        return cards.size();
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public int calculate() {
        final int sumOfCards = cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum();

        if (sumOfCards >= BUST_LIMIT && howManyAce() > 0) {
            return calculateScoreWithAce(sumOfCards);
        }
        return sumOfCards;
    }

    private int calculateScoreWithAce(final int sum) {
        int aceCount = howManyAce();
        int changedSum = sum;
        while (aceCount-- > 0 && changedSum >= BUST_LIMIT) {
            changedSum = changedSum - CardNumber.ACE.getValue() + CardNumber.ACE.getExtraValue();
        }
        return changedSum;
    }

    private int howManyAce() {
        return (int) cards.stream()
            .filter(card -> card.getCardNumber().isAce())
            .count();
    }

}
