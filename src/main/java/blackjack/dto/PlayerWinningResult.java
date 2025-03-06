package blackjack.dto;

import blackjack.domain.GameResultType;
import blackjack.domain.Nickname;

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
