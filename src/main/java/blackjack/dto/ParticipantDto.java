package blackjack.dto;

import java.util.List;

public abstract class ParticipantDto {
    private final String name;
    private final List<String> cards;
    private final int score;

    protected ParticipantDto(String name, List<String> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ParticipantDto{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                ", score=" + score +
                '}';
    }
}
