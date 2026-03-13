package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.PlayerGroup;
import java.util.List;
import java.util.stream.Stream;

public record ParticipantScoreDtos(List<ParticipantScoreDto> scoreDtos) {

    public static ParticipantScoreDtos of(Dealer dealer, PlayerGroup playerGroup) {
        List<ParticipantScoreDto> scoreDtos = participantStream(dealer, playerGroup)
            .map(ParticipantScoreDtos::from)
            .toList();
        return new ParticipantScoreDtos(scoreDtos);
    }

    private static ParticipantScoreDto from(Participant participant) {
        return ParticipantScoreDto.from(participant, participant.getScore());
    }

    private static Stream<Participant> participantStream(Dealer dealer, PlayerGroup playerGroup) {
        return Stream.concat(
            Stream.of(dealer),
            playerGroup.players().stream()
        );
    }
}
