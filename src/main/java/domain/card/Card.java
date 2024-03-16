package domain.card;

import dto.CardResponse;

public record Card(Rank rank, Suit suit) {
    public CardResponse toCardResponse() {
        return new CardResponse(rank.name(), suit.name());
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }
}
