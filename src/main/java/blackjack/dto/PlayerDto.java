package blackjack.dto;

public abstract class PlayerDto {
    private final String name;
    private final HandDto hand;
    private final int score;

    protected PlayerDto(String name, HandDto hand, int score) {
        this.name = name;
        this.hand = hand;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public HandDto getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }
}
