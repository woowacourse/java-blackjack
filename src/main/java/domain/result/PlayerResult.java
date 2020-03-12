package domain.result;

public class PlayerResult extends BlackJackResult<WinLose> {
    private final WinLose winLose;

    public PlayerResult(String name, WinLose winLose) {
        super(name);
        this.winLose = winLose;
    }

    @Override
    public WinLose getWinLose() {
        return winLose;
    }

    @Override
    public String toString() {
        return getGamerName() + ": " + winLose.name();
    }
}
