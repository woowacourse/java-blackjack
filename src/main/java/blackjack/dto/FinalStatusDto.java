package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalStatusDto(
    List<String> nicknames,
    Map<String, List<String>> nicknameCardNamesMap,
    Map<String, Integer> nicknameScoreMap
) {

    public static FinalStatusDto from(final Participants participants) {
        final List<String> nicknames = new ArrayList<>();
        final Map<String, List<String>> nicknameCardNamesMap = new LinkedHashMap<>();
        final Map<String, Integer> nicknameScoreMap = new LinkedHashMap<>();

        participants.all().forEach(participant -> {
            nicknames.add(participant.getNickname());
            nicknameCardNamesMap.put(participant.getNickname(), participant.getAllCardNames());
            nicknameScoreMap.put(participant.getNickname(), participant.getScore());
        });

        return new FinalStatusDto(nicknames, nicknameCardNamesMap, nicknameScoreMap);
    }

}
