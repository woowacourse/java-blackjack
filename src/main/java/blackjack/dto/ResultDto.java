package blackjack.dto;

import java.util.Map;

public final class ResultDto {

    private final int dealerResult;
    private final Map<String, Integer> participantsResult;

    public ResultDto(int dealerResult, Map<String, Integer> participantsResult) {
        this.dealerResult = dealerResult;
        this.participantsResult = participantsResult;
    }

    public int getDealerResult() {
        return dealerResult;
    }

    public Map<String, Integer> getParticipantsResult() {
        return participantsResult;
    }
}
