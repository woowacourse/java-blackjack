package domain.dto;

import domain.Card;
import domain.Gamer;
import java.util.List;

public class GamerDto {
    private final String name;
    private final List<Card> cards;
    private final int totalScore;

    private GamerDto(String name, List<Card> cards, int totalScore) {
        this.name = name;
        this.cards = cards;
        this.totalScore = totalScore;
    }

    public static GamerDto from(Gamer gamer) {
        // TODO: 디미터 법칙 위반
        return new GamerDto(
                gamer.getName().getName(),
                gamer.getCards(),
                gamer.getTotalScore()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
