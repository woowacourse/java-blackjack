package blackjack.model;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // 카드의 점수를 반환한다.
    public int score() {
        return rank.getDefaultScore();
    }

    // 에이스인지 판별
    public boolean isAce() {
        return rank == Rank.ACE;
    }
}
