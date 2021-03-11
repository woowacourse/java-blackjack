package blackjack.domain.result;

import blackjack.domain.card.Cards;
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
        if (dealer.isBust() || gamer.isBust()) {
            return judgeBustResult(dealer, gamer);
        }
        return judgeNoneBustResult(dealer, gamer);
    }

    private static Map<Player, ResultType> judgeBustResult(Dealer dealer, Gamer gamer) {
        Map<Player, ResultType> result = new HashMap<>();
        if (dealer.isBust()) {
            result.put(dealer, LOSE);
            result.put(gamer, WIN);
            return result;
        }
        result.put(dealer, WIN);
        result.put(gamer, LOSE);
        return result;
    }

    private static Map<Player, ResultType> judgeNoneBustResult(Dealer dealer, Gamer gamer) {
        Map<Player, ResultType> result = new HashMap<>();
        int dealerScore = Cards.BLACK_JACK - dealer.calculateScore();
        int gamerScore = Cards.BLACK_JACK - gamer.calculateScore();
        if (dealerScore < gamerScore) {
            result.put(dealer, WIN);
            result.put(gamer, LOSE);
            return result;
        }
        if (dealerScore == gamerScore) {
            result.put(dealer, DRAW);
            result.put(gamer, DRAW);
            return result;
        }
        result.put(dealer, LOSE);
        result.put(gamer, WIN);
        return result;
    }

    public String getValue() {
        return value;
    }
}
