package blackjack;

public class GamePlayerResult {
    Name name;
    ResultStatus resultStatus;

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
