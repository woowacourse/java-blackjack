package domain.participant.dto;

import domain.participant.Participant;

public record PlayerResultDto(PlayerHandDto playerHand, int resultScore) {
    public static PlayerResultDto from(Participant participant) {
        int resultScore = participant.getResultScore();
        return new PlayerResultDto(PlayerHandMapper.from(participant), resultScore);
    }
}
