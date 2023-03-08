package blackjack.domain;

public class Result {

    private final String name;
    private final WinResult winResult;

    public Result(String name, WinResult winResult) {
        this.name = name;
        this.winResult = winResult;
    }

    public String getName() {
        return name;
    }

    public WinResult getWinResult() {
        return winResult;
    }
}
