package domains.result;

public enum ResultType {
    WIN("승"), LOSE("패"), DRAW("무");

    private String winOrDrawOrLose;

    ResultType(String winOrDrawOrLose) {
        this.winOrDrawOrLose = winOrDrawOrLose;
    }

    public String getWinOrDrawOrLose() {
        return winOrDrawOrLose;
    }
}
