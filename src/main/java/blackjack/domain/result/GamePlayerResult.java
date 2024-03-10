package blackjack.domain.result;

import blackjack.domain.common.Name;

public class GamePlayerResult {
    private final Name name;
    private final ResultStatus resultStatus;

    public GamePlayerResult(final Name name, final ResultStatus resultStatus) {
        this.name = name;
        this.resultStatus = resultStatus;
    }

    public String getName() {
        return name.asString();
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
