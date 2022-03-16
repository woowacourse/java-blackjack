package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Map;

public enum Result {

    WIN("승리"),
    LOSE("패배"),
    TIE("무승부"),
    ;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static Map<Player, Result> judgeResult(Dealer dealer, Players players) {
        Map<Player, Result> resultCounter = players.judgeResult(dealer.calculateBestScore());
        return resultCounter;
    }
}
