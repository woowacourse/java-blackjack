package domain.result;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.Map;

public class Result {

    private static final String NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] 딜러가 BlackJack 이 아닙니다.";

    private final Map<Name, Versus> playerResults;

    private Result(Map<Name, Versus> playerResults) {
        this.playerResults = Map.copyOf(playerResults);
    }

    public static Result generateResultAtDealerBlackJack(Dealer dealer, Players players) {
        validateDealerIsBlackJack(dealer);
        return new Result(players.compareAtDealerBlackJack(dealer));
    }

    private static void validateDealerIsBlackJack(Dealer dealer) {
        if (!dealer.isBlackJack()) {
            throw new IllegalStateException(NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE);
        }
    }

    public static Result generateResultAtFinal(Dealer dealer, Players players) {
        return new Result(players.compareResultAtFinal(dealer));
    }

    public Versus getVersusOfPlayer(Name name) {
        return playerResults.get(name);
    }

    public int countDealerWin() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == Versus.LOSE)
                .count();
    }

    public int countDealerDraw() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == Versus.DRAW)
                .count();
    }

    public int countDealerLose() {
        return (int) playerResults.keySet().stream()
                .filter(key -> playerResults.get(key) == Versus.WIN)
                .count();
    }
}
