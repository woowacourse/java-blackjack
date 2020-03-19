package second.domain.result;

import second.domain.gamer.Dealer;
import second.domain.gamer.Player;

import java.util.Arrays;

public enum ResultType {
    ONLY_PLAYER_BLACK_JACK("플레이어 블랙잭", new OnlyMeBlackJackResultTypeStrategy()),
    BOTH_BLACK_JACK("딜러 플레이어 모두 블랙잭", new BothBlackJackResultTypeStrategy()),
    WIN("승", new WinResultTypeStrategy()),
    LOSE("패", new LoseResultTypeStrategy());

    private final String name;
    private final ResultTypeStrategy resultJudge;

    ResultType(final String name, final ResultTypeStrategy resultJudge) {
        this.name = name;
        this.resultJudge = resultJudge;
    }

    // TODO :  이기거나 진사람이 없을경우 Exception 발생
    public static ResultType of(final Player player, final Dealer dealer) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.resultJudge.judge(player, dealer))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public String getName() {
        return name;
    }
}