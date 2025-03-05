package domain.card;

public class Card {

    private final CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public int getScore() {
        return type.getScore();
    }

    public CardType getType() {
        return type;
    }

    public boolean isAce() {
        return type.getScore() == 1;
    }

    public int calculateAceScore(final int score, final int limit) {
        final int result = limit - score;
        int aceScore = 1;
        if (result >=  11) {
            aceScore = 11;
        }
        return aceScore;
    }
}
