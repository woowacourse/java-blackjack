package blackjack.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.participant.Participants;

public class ParticipantsDto {

    private final ParticipantDto dealer;
    private final List<ParticipantDto> players;

    private ParticipantsDto(ParticipantDto dealer, List<ParticipantDto> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static ParticipantsDto from(Participants participants) {
        List<ParticipantDto> participantDtos = participants.getPlayers().stream()
            .map(ParticipantDto::from)
            .collect(Collectors.toList());
        return new ParticipantsDto(ParticipantDto.from(participants.getDealer()), participantDtos);
    }

    public ParticipantDto getDealer() {
        return dealer;
    }

    public List<ParticipantDto> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
