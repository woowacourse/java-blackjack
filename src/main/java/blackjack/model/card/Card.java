package blackjack.model.card;

import blackjack.dto.CardDto;

public class Card {

    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getDefaultScore() {
        return rank.getDefaultScore();
    }

    public CardDto toDto() {
        return new CardDto(this.rank, this.suit);
    }
}
