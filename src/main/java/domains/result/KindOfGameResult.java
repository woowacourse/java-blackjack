package domains.result;

public enum KindOfGameResult {
    WIN("승"), LOSE("패"), DRAW("무");

    private String winOrDrawOrLose;

    KindOfGameResult(String winOrDrawOrLose) {
        this.winOrDrawOrLose = winOrDrawOrLose;
    }

    public String getWinOrDrawOrLose() {
        return winOrDrawOrLose;
    }
}
