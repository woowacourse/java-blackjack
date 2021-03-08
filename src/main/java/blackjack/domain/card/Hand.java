package blackjack.domain.card;

import static blackjack.domain.card.Rank.ACE_VALUE;
import static blackjack.domain.participant.Dealer.TWENTY_ONE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        return sumAceToOne() > TWENTY_ONE;
    }

    private int sumAceToOne() {
        return getTotalByMapper(Card::getRankValue);
    }

    public int getDealerTotal() {
        return getTotalByMapper(this::getAceValue);
    }

    private int getTotalByMapper(ToIntFunction<Card> mapper) {
        return cards.stream()
                .mapToInt(mapper)
                .sum();
    }

    private int getAceValue(Card card) {
        if (card.isAce()) {
            return ACE_VALUE;
        }
        return card.getRankValue();
    }


    public int getPlayerTotal() {
        int aceCount = getCountOfAce();
        int result = (aceCount * ACE_VALUE) + getTotalExceptAce();

        while (aceCount >= 0 && TWENTY_ONE < result) {
            result -= 10;
            aceCount--;
        }

        return result;
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int getTotalExceptAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getRankValue)
                .sum();
    }
}
