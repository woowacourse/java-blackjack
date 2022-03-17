package blackjack.dto;

public abstract class PlayerDto {
    private final String name;
    private final HandDto deck;
    private final int score;

    protected PlayerDto(String name, HandDto deck, int score) {
        this.name = name;
        this.deck = deck;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public HandDto getHand() {
        return deck;
    }

    public int getScore() {
        return score;
    }
}
