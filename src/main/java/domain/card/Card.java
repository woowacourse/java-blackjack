package domain.card;

public class Card {

    private final CardScore score;
    private final CardKind kind;

    public Card(String score, String kind) {
        this.score = CardScore.of(score);
        this.kind = CardKind.of(kind);
    }

    public int getScore() {
        return score.getNumber();
    }

    public String getInfo() {
        return score.getScore() + kind.getKind();
    }

    public boolean isAce() {
        return score == CardScore.ACE;
    }
}
