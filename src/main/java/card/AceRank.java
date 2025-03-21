package card;

public enum AceRank implements Rank {

    SOFT_ACE(11),
    HARD_ACE(1);

    private final int score;

    AceRank(int score) {
        this.score = score;
    }

    @Override
    public boolean matches(Rank rank) {
        return this == rank;
    }

    @Override
    public int getScore() {
        return score;
    }

}
