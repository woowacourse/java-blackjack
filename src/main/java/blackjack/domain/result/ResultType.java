package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    ResultType(String value) {
        this.value = value;
    }

    public static Map<Player, ResultType> judgeGameResult(Dealer dealer, Gamer gamer) {
        Map<Player, ResultType> result = new HashMap<>();
        if (dealer.isBust()) {
            putResult(result, dealer, gamer, LOSE, WIN);
            return result;
        }
        if (gamer.isBust()) {
            putResult(result, dealer, gamer, WIN, LOSE);
            return result;
        }
        judgeNotBustGameResult(dealer, gamer, result);
        return result;
    }

    private static void judgeNotBustGameResult(Dealer dealer, Gamer gamer, Map<Player, ResultType> result) {
        int dealerScore = dealer.calculateScore();
        int gamerScore = gamer.calculateScore();
        if (dealerScore > gamerScore) {
            putResult(result, dealer, gamer, WIN, LOSE);
        }
        if (dealerScore == gamerScore) {
            putResult(result, dealer, gamer, DRAW, DRAW);
        }
        if (dealerScore < gamerScore) {
            putResult(result, dealer, gamer, LOSE, WIN);
        }
    }

    private static void putResult(Map<Player, ResultType> result, Dealer dealer, Gamer gamer, ResultType dealerResult, ResultType gamerResult) {
        result.put(dealer, dealerResult);
        result.put(gamer, gamerResult);
    }

    public String getValue() {
        return value;
    }
}
