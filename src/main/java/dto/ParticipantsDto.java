package dto;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public record ParticipantsDto(List<ParticipantDto> participants) {

    public static ParticipantsDto from(final Dealer dealer, final Players players) {
        final List<ParticipantDto> result = new ArrayList<>();
        result.add(ParticipantDto.from(dealer));

        for (Player player : players.getPlayers()) {
            result.add(ParticipantDto.from(player));
        }

        return new ParticipantsDto(result);
    }

    public static ParticipantsDto from(final Players players) {
        final List<ParticipantDto> result = new ArrayList<>();

        for (Participant player : players.getPlayers()) {
            result.add(ParticipantDto.from(player));
        }
        
        return new ParticipantsDto(result);
    }

    public List<String> getNames() {
        return participants.stream()
                .map(ParticipantDto::name)
                .toList();
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }
}
