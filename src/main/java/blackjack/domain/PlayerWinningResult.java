package blackjack.domain;

public class PlayerWinningResult {

    private final Nickname nickname;
    private final GameResultType result;

    public PlayerWinningResult(Nickname nickname, GameResultType result) {
        this.nickname = nickname;
        this.result = result;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public GameResultType getResult() {
        return result;
    }
}
