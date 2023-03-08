package blackjack.dto;

import java.util.Map;

public class ChallengerResultDto {

    private final Map<String, String> nameAndResult;

    public ChallengerResultDto(Map<String, String> nameAndResult) {
        this.nameAndResult = nameAndResult;
    }

    public Map<String, String> getNameAndResult() {
        return nameAndResult;
    }
}
