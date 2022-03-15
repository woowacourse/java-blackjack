package blackjack.dto;

public abstract class PlayerDTO {
    private final String name;
    private final DeckDTO deck;
    private final int score;

    protected PlayerDTO(String name, DeckDTO deck, int score) {
        this.name = name;
        this.deck = deck;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public DeckDTO getDeck() {
        return deck;
    }

    public int getScore() {
        return score;
    }
}
