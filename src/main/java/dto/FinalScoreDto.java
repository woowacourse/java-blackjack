package dto;

import java.util.List;

public record FinalScoreDto(ParticipantScoreDto dealer, List<ParticipantScoreDto> players) {
    public static FinalScoreDto from(ParticipantScoreDto dealer, List<ParticipantScoreDto> players) {
        return new FinalScoreDto(dealer, players);
    }
}
