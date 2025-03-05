package blackjack.domain;

import java.util.Set;

public class Card {

    private final CardSuit suit;
    private final CardRank rank;

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Set<Integer> checkScore() {
        return rank.getScore();
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getName();
    }
}
