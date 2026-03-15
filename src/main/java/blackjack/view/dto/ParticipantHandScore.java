package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.view.CardMapper;
import java.util.List;
import java.util.stream.Stream;

public record ParticipantHandScore(
        String nickname,
        List<String> cardDisplayNames,
        int totalScore
) {

    public static ParticipantHandScore from(Participant participant) {
        return new ParticipantHandScore(
                participant.getNickname(),
                toCardDisplayNames(participant),
                participant.getScore()
        );
    }

    public static List<ParticipantHandScore> listOf(List<Player> players, Dealer dealer) {
        List<ParticipantHandScore> participantHandScores = players.stream()
                .map(ParticipantHandScore::from)
                .toList();
        return Stream
                .concat(Stream.of(ParticipantHandScore.from(dealer)), participantHandScores.stream())
                .toList();
    }

    private static List<String> toCardDisplayNames(Participant participant) {
        return participant.getCards().stream()
                .map(CardMapper::toDisplayName)
                .toList();
    }
}
