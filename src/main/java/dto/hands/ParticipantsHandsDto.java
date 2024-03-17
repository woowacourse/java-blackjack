package dto.hands;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public record ParticipantsHandsDto(List<ParticipantHandsDto> participants) {

    public static ParticipantsHandsDto of(final Dealer dealer, final Players players) {
        final List<ParticipantHandsDto> result = new ArrayList<>();
        result.add(ParticipantHandsDto.from(dealer));

        for (Player player : players.getPlayers()) {
            result.add(ParticipantHandsDto.from(player));
        }

        return new ParticipantsHandsDto(result);
    }

    public static ParticipantsHandsDto from(final Players players) {
        final List<ParticipantHandsDto> result = new ArrayList<>();

        for (Participant player : players.getPlayers()) {
            result.add(ParticipantHandsDto.from(player));
        }

        return new ParticipantsHandsDto(result);
    }

    public List<String> getNames() {
        return participants.stream()
                .map(ParticipantHandsDto::name)
                .toList();
    }

    public List<ParticipantHandsDto> getParticipants() {
        return participants;
    }
}
