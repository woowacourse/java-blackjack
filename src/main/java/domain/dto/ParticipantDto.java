package domain.dto;

import domain.card.Card;
import domain.participant.Name;
import domain.participant.Participant;

import java.util.List;

public record ParticipantDto(Name name, List<Card> cards, int score) {

    public static ParticipantDto from(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }
}
