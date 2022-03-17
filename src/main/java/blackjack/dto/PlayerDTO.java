package blackjack.dto;

public abstract class PlayerDTO {
    private final String name;
    private final HandDTO deck;
    private final int score;

    protected PlayerDTO(String name, HandDTO deck, int score) {
        this.name = name;
        this.deck = deck;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public HandDTO getHand() {
        return deck;
    }

    public int getScore() {
        return score;
    }
}
