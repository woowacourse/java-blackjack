package dto;

import java.util.List;

public record InitialDto(String joinedNames, String firstCardName, List<ParticipantDto> players) {
    public static InitialDto from(String joinedNames, String firstCardName, List<ParticipantDto> players) {
        return new InitialDto(joinedNames, firstCardName, players);
    }
}
