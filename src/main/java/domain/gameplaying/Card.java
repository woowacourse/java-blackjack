package domain.gameplaying;

public record Card(CardRank rank, CardMark cardMark) {

    boolean isAce() {
        return rank == CardRank.ACE;
    }

    int score() {
        return rank.score();
    }

    @Override
    public String toString() {
        return rank().label() + cardMark().description();
    }
}
