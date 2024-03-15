package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ParticipantsDto(List<ParticipantDto> participantDtos) {

    public static ParticipantsDto toDtoWithoutDealer(final Players players) {
        final List<Participant> participants = new ArrayList<>(players.getPlayers());

        return generateParticipantDtos(participants);
    }

    public static ParticipantsDto toDtoWithDealer(final Dealer dealer, final Players players) {
        final List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());

        return generateParticipantDtos(participants);
    }

    private static ParticipantsDto generateParticipantDtos(final List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDto::from)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ParticipantsDto::new));
    }
}
