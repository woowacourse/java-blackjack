package blackjack.controller;

import blackjack.domain.participant.Participant;
import blackjack.dto.ParticipantDto;

public class ModelMapper {

    private ModelMapper() {
    }

    static ParticipantDto map(Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }
}
