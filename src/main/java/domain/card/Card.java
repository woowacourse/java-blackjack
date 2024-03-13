package domain.card;

import dto.CardResponse;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardResponse toCardResponse() {
        return new CardResponse(rank.name(), suit.name());
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSymbol() {
        return suit;
    }
}
