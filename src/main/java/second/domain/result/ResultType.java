package second.domain.result;

import second.domain.gamer.Dealer;
import second.domain.gamer.Player;

import java.util.Arrays;

public enum ResultType {
    ONLY_PLAYER_BLACK_JACK("플레이어 블랙잭", (gamer, counterGamer) -> gamer.isBlackJack() && !counterGamer.isBlackJack(), 1.5),
    DRAW("무승부", new DrawResultTypeStrategy(), 0),
    WIN("승", new WinResultTypeStrategy(), 1),
    LOSE("패", new LoseResultTypeStrategy(), -1);

    private final String name;
    private final ResultTypeStrategy resultJudge;
    private final double profitMultipleValue;

    ResultType(final String name, final ResultTypeStrategy resultJudge, final double profitMultipleValue) {
        this.name = name;
        this.resultJudge = resultJudge;
        this.profitMultipleValue = profitMultipleValue;
    }

    public static ResultType of(final Player player, final Dealer dealer) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.resultJudge.judge(player, dealer))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public String getName() {
        return name;
    }

    public double getProfitMultipleValue() {
        return profitMultipleValue;
    }
}