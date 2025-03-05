package domain;

public class Player {
    private static final int LIMIT = 21;

    private final String name;
    private final CardGroup cardGroup;

    public Player(final String name, final CardGroup cardGroup) {
        this.name = name;
        this.cardGroup = cardGroup;
    }

    public boolean receiveCard(final Card card) {
        return cardGroup.addCard(card);
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore(LIMIT) > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        return GameResult.findByScores(cardGroup.calculateScore(LIMIT), compareScore);
    }

}
