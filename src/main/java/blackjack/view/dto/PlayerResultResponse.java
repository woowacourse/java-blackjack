package blackjack.view.dto;

import blackjack.domain.result.Result;

public class PlayerResultResponse {

    private final String name;
    private final Result result;

    public PlayerResultResponse(final String name, final Result result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
