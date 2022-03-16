package blackjack.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.participant.Participant;

public class ProfitResultDto {

    private final Map<ParticipantDto, Integer> result;

    private ProfitResultDto(Map<ParticipantDto, Integer> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public static ProfitResultDto from(Map<Participant, Integer> result) {
        Map<ParticipantDto, Integer> resultDto = new LinkedHashMap<>();
        for (Map.Entry<Participant, Integer> entry : result.entrySet()) {
            resultDto.put(ParticipantDto.from(entry.getKey()), entry.getValue());
        }
        return new ProfitResultDto(resultDto);
    }

    public Map<ParticipantDto, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
