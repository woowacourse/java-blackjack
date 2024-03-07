package blackjack.domain.result;

import blackjack.domain.common.Name;

public class GamePlayerResult {
    private final Name name;
    private final ResultStatus resultStatus;

    public GamePlayerResult(Name name, ResultStatus resultStatus) {
        this.name = name;
        this.resultStatus = resultStatus;
    }

    public Name getName() {
        return name;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
