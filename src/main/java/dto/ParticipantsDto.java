package dto;

import domain.participant.Participant;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class ParticipantsDto { // TODO: parit dto

    private final List<ParticipantDto> players;

    private ParticipantsDto(final List<ParticipantDto> players) {
        this.players = players;
    }

    public static ParticipantsDto of(final Participant dealer, final Players players) {
        List<ParticipantDto> result = new ArrayList<>();
        result.add(ParticipantDto.from(dealer));
        for (Participant player : players.getPlayers()) {
            result.add(ParticipantDto.from(player));
        }
        return new ParticipantsDto(result);
    }

    public static ParticipantsDto of(final Players players) {
        List<ParticipantDto> result = new ArrayList<>();
        for (Participant player : players.getPlayers()) {
            result.add(ParticipantDto.from(player));
        }
        return new ParticipantsDto(result);
    }

    public List<String> getNames() {
        return players.stream()
                .map(ParticipantDto::getName)
                .toList();
    }

    public List<ParticipantDto> getPlayers() {
        return players;
    }
}
