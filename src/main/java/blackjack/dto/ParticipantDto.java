package blackjack.dto;

import blackjack.domain.card.CardHand;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Participant;

public record ParticipantDto(String name, CardHandDto cardHandDto, int score) {

    public static ParticipantDto from(final Participant participant) {
        final String name = participant.getName();
        final CardHandDto cardHandDto = toCardHandDto(participant);
        final Score score = participant.calculateScore();

        return new ParticipantDto(name, cardHandDto, score.getScore());
    }

    private static CardHandDto toCardHandDto(final Participant participant) {
        final CardHand cardHand = participant.getCardHand();
        return CardHandDto.from(cardHand);
    }
}
