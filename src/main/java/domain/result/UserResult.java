package domain.result;

public class UserResult extends BlackJackResult<WinLose> {
    private final WinLose winLose;

    public UserResult(String name, WinLose winLose) {
        super(name);
        this.winLose = winLose;
    }

    @Override
    public WinLose getWinLose() {
        return winLose;
    }
}
