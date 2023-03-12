package domain.participant;

import java.util.List;
import java.util.Map;

public class ParticipantFinalResultDto {

    private final ParticipantResultDto participantResultDto;
    private final int totalValueSum;

    public ParticipantFinalResultDto(ParticipantResultDto participantResultDto, int totalValueSum) {
        this.participantResultDto = participantResultDto;
        this.totalValueSum = totalValueSum;
    }

    public ParticipantResultDto getParticipantResultDto() {
        return participantResultDto;
    }

    public int getTotalValueSum() {
        return totalValueSum;
    }
}
