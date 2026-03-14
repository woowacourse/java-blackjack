package blackjack.dto;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.List;
import java.util.stream.Stream;

public record ParticipantScoreDtos(List<ParticipantScoreDto> scoreDtos) {

    public static ParticipantScoreDtos of(Dealer dealer, List<Player> players) {
        List<ParticipantScoreDto> scoreDtos = participantStream(dealer, players)
            .map(ParticipantScoreDtos::from)
            .toList();
        return new ParticipantScoreDtos(scoreDtos);
    }

    private static Stream<Participant> participantStream(Dealer dealer, List<Player> players) {
        return Stream.concat(
            Stream.of(dealer),
            players.stream()
        );
    }

    private static ParticipantScoreDto from(Participant participant) {
        return ParticipantScoreDto.from(participant, participant.getScore());
    }
}
