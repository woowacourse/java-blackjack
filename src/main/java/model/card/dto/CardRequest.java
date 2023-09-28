package model.card.dto;

public class CardRequest {

    private final String name;
    private final int score;

    private CardRequest(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static CardRequest createDefault(final String name, final int score) {
        return new CardRequest(name, score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
