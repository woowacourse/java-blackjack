package blakcjack.dto;

import blakcjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantsDto {
    private final ParticipantDto dealer;
    private final List<ParticipantDto> players;

    private ParticipantsDto(final ParticipantDto dealer, final List<ParticipantDto> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static ParticipantsDto of(final Participant dealer, final List<Participant> players) {
        return new ParticipantsDto(ParticipantDto.from(dealer),
                players.stream()
                        .map(ParticipantDto::from)
                        .collect(Collectors.toList())
        );
    }

    public ParticipantDto getDealer() {
        return dealer;
    }

    public List<ParticipantDto> getPlayers() {
        return players;
    }
}
