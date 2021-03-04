package blackjack.dto;

import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantsDto {
    private final List<Participant> participants;

    private ParticipantsDto(List<Participant> participants) {
        this.participants = participants;
    }

    public static ParticipantsDto valueOf(Dealer dealer, Players players) {
        List<Participant> participants = new ArrayList<>(Collections.singletonList(dealer));
        participants.addAll(players.unwrap());
        return new ParticipantsDto(participants);
    }

    public List<Participant> unwrap() {
        return new ArrayList<>(participants);
    }
}
