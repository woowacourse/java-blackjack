package domains.result;

public enum WinOrDrawOrLose {
    WIN("승"), LOSE("패"), DRAW("무");

    private String winOrDrawOrLose;

    WinOrDrawOrLose(String winOrDrawOrLose) {
        this.winOrDrawOrLose = winOrDrawOrLose;
    }

    public String getWinOrDrawOrLose() {
        return winOrDrawOrLose;
    }
}
