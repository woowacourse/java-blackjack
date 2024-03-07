package blackjack.dto;

import blackjack.domain.Hands;
import blackjack.domain.ParticipantsName;
import blackjack.domain.Score;
import java.util.LinkedHashMap;
import java.util.Map;

public record FinalResultDTO(Map<String, HandsScoreDTO> finalCards) {

    public static FinalResultDTO of(final Map<ParticipantsName, Hands> playersHands,
                                    final Map<ParticipantsName, Score> playersScores) {
        Map<String, HandsScoreDTO> finalCards = new LinkedHashMap<>();
        playersHands.forEach((key, value) ->
                finalCards.put(key.getName(), HandsScoreDTO.of(value, playersScores.get(key))));

        return new FinalResultDTO(finalCards);
    }
}
