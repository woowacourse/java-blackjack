package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import domain.game.GameRule;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getDenomination().value();
        }

        sum = decideAceNumber(sum, countAce());

        return sum;
    }

    private int decideAceNumber(int sum, int unDecidedAceCount) {
        if (sum > GameRule.BLACKJACK.getNumber() && unDecidedAceCount > 0) {
            sum = sum - (Denomination.ACE.value() - GameRule.MIN_ACE.getNumber());
            return decideAceNumber(sum, unDecidedAceCount - 1);
        }
        return sum;
    }

    public boolean isOverBlackjack() {
        return sumCardNumbers() > GameRule.BLACKJACK.getNumber();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.getDenomination() == Denomination.ACE)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
