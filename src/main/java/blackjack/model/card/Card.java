package blackjack.model.card;

public class Card {

    private final Rank rank;
    private final Suit suit;
    private boolean isOpened;

    private Card(Rank rank, Suit suit, boolean isOpened) {
        this.rank = rank;
        this.suit = suit;
        this.isOpened = isOpened;
    }

    public static Card opened(Rank rank, Suit suit) {
        return new Card(rank, suit, true);
    }

    public void flip() {
        this.isOpened = false;
    }

    public int getDefaultScore() {
        return rank.getDefaultScore();
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    public CardDto toDto() {
        return new CardDto(this.rank, this.suit);
    }
}
