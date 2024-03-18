package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitResultDto;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class MessageGenerator {
    private static final String NEWLINE_DELIMITER = System.lineSeparator();
    private static final String COMMA_DELIMITER = ", ";

    protected String generatePlayerNames(List<ParticipantDto> players) {
        return players.stream()
                .map(ParticipantDto::name)
                .collect(joining(COMMA_DELIMITER));
    }

    protected String generateCardsOfAllParticipant(List<ParticipantDto> participantDtos) {
        return participantDtos.stream()
                .map(this::generateCardsOfParticipant)
                .collect(joining(NEWLINE_DELIMITER));
    }

    protected String generateCardsOfParticipant(ParticipantDto participant) {
        String name = participant.name();
        String cardsInfo = generateCardsInfo(participant.cards());

        return String.format("%s카드: %s", name, cardsInfo);
    }

    protected String generateCardsInfo(List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.denomination() + cardDto.suit())
                .collect(joining(COMMA_DELIMITER));
    }

    protected String generateCardsWithScore(List<ParticipantDto> participants) {
        return participants.stream()
                .map(this::generateCardsWithScoreMessage)
                .collect(joining(NEWLINE_DELIMITER));
    }

    private String generateCardsWithScoreMessage(ParticipantDto participant) {
        String cardsOfParticipant = generateCardsOfParticipant(participant);
        int score = participant.score();

        return String.format("%s - 결과: %d", cardsOfParticipant, score);
    }

    protected String generateProfits(ProfitResultDto profitResult) {
        Map<String, Integer> result = profitResult.result();

        return result.keySet()
                .stream()
                .map(name -> String.format("%s: %d", name, result.get(name)))
                .collect(joining(NEWLINE_DELIMITER));
    }
}
