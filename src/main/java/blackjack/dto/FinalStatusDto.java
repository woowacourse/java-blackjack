package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalStatusDto(
    List<String> participantNicknames,
    Map<String, List<String>> participantCardNamesMap,
    Map<String, Integer> participantScoreMap
) {

    public static FinalStatusDto from(final Participants participants) {
        final List<String> participantNicknames = participants.all().stream()
            .map(Participant::getNickname)
            .toList();
        final Map<String, List<String>> participantCardNamesMap = new LinkedHashMap<>();
        participants.all().forEach(participant ->
            participantCardNamesMap.put(participant.getNickname(), participant.getAllCardNames()));
        final Map<String, Integer> participantScoreMap = new LinkedHashMap<>();
        participants.all().forEach(participant ->
            participantScoreMap.put(participant.getNickname(), participant.getScore()));

        return new FinalStatusDto(participantNicknames, participantCardNamesMap,
            participantScoreMap);
    }

}
