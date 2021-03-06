package blackjack.controller.dto;

import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import java.util.Map;

public class ResultDTO {
    private final Map<Name, ResultType> result;

    public ResultDTO(Map<Name, ResultType> result) {
        this.result = result;
    }

    public Map<Name, ResultType> getResult() {
        return result;
    }
}
