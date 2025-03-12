package blackjack.dto;

import java.util.Arrays;

import blackjack.domain.RoundResult;

public enum RoundResultDto {
    WIN("승"),
    LOSE("패"),
    TIE("무"),
    ;

    private final String displayName;

    RoundResultDto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static RoundResultDto from(RoundResult roundResult) {
        return Arrays.stream(values())
            .filter(roundResultDto -> roundResultDto.name().equals(roundResult.name()))
            .findAny()
            .orElse(null);
    }
}
