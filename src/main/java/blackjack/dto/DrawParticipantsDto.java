package blackjack.dto;


import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;

public class DrawParticipantsDto {

    private final List<Participant> all;

    private DrawParticipantsDto(List<Participant> all) {
        this.all = all;
    }

    public static DrawParticipantsDto from(Participants participants) {
        return new DrawParticipantsDto(participants.getAll());
    }

    public List<Participant> all() {
        return all;
    }
}
