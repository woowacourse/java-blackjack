package team.blackjack.domain;

import java.util.List;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getCardName() {
        return this.rank.name() + this.suit.getKoreanName();
    }

    public boolean isAce() {
        return this.rank.isAce();
    }

    public List<Integer> getScore() {
        return this.rank.getScore();
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }
}
