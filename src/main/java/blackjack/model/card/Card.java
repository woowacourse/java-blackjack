package blackjack.model.card;

public class Card {

    private final Rank rank;
    private final Suit suit;
    private boolean isOpened;

    private Card(Rank rank, Suit suit, boolean isOpened) {
        validate(rank, suit);

        this.rank = rank;
        this.suit = suit;
        this.isOpened = isOpened;
    }

    private void validate(
            Rank rank,
            Suit suit
    ) {
        if (rank == null) {
            throw new IllegalArgumentException("rank가 null입니다.");
        }

        if (suit == null) {
            throw new IllegalArgumentException("rank가 null입니다.");
        }
    }

    public static Card createOpenedCard(
            Rank rank,
            Suit suit
    ) {
        return new Card(rank, suit, true);
    }

    public void flip() {
        this.isOpened = false;
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getDefaultScore() {
        return rank.getDefaultScore();
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    @Override
    public String toString() {
        return rank.getDisplayName() + suit.getDisplayName();
    }
}
