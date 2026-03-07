package blackjack.domain;

public class Card {

    private static final String ACE_RANK_NAME = "A";
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return ACE_RANK_NAME.equals(rank.getRankName());
    }

    public String getDisplayName() {
        return String.format("%s%s", rank.getRankName(), suit.getSuitDisplayName());
    }
}
