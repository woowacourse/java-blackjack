package blackjack.dto;

import blackjack.domain.card.Score;
import blackjack.domain.participant.Participant;

import java.util.List;

public record ParticipantDto(String name, List<CardDto> cards, int score) {

    public static ParticipantDto from(final Participant participant) {
        final String name = participant.getName();
        final CardHandDto cardHandDto = CardHandDto.from(participant.getCardHand());
        final Score score = participant.calculateScore();

        return new ParticipantDto(name, cardHandDto.cards(), score.getScore());
    }
}
