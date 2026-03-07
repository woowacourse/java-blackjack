package blackjack.dto;

import blackjack.model.Participant;
import blackjack.model.Score;
import blackjack.model.ScoreCalculator;
import java.util.List;

public record ParticipantScoreDto(
    String name,
    List<CardDto> cards,
    Score score
) {
    public static ParticipantScoreDto from(Participant participant, ScoreCalculator calculator) {
        List<CardDto> cards = participant.getCards()
            .stream()
            .map(CardDto::from)
            .toList();

        return new ParticipantScoreDto(
            participant.getName(),
            cards,
            calculator.calculate(participant.getCards())
        );
    }
}
