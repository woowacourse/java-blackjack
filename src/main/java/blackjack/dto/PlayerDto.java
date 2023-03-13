package blackjack.dto;

import java.util.List;

public final class PlayerDto {

    public static final int DEFAULT_SCORE = 0;

    private final String name;
    private final List<String> cards;
    private final int score;

    private PlayerDto(String name, List<String> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static PlayerDto of(String name, List<String> cards) {
        return new PlayerDto(name, cards, DEFAULT_SCORE);
    }

    public static PlayerDto of(String name, List<String> cards, int score) {
        return new PlayerDto(name, cards, score);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
