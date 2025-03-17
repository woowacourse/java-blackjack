package view;

import domain.card.Card;
import domain.participant.Gambler;
import java.util.List;

public record GamblerDto(
    String name,
    List<Card> cards,
    int score
) {

    public static GamblerDto from(Gambler gambler) {
        return new GamblerDto(
            gambler.getName(),
            gambler.getCards(),
            gambler.calculateScore()
        );
    }
}
