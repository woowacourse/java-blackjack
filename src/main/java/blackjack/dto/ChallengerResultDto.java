package blackjack.dto;

import java.util.Map;

public class ChallengerResultDto {

    private final Map<String, Integer> nameAndResult;

    public ChallengerResultDto(Map<String, Integer> nameAndResult) {
        this.nameAndResult = nameAndResult;
    }

    public Map<String, Integer> getNameAndResult() {
        return nameAndResult;
    }
}
