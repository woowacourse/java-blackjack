package domain.result;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.Map;

public class Result {

    private static final String ABSENT_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름이 존재하지 않습니다.";
    private static final String NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] 딜러가 BlackJack 이 아닙니다.";

    private final Map<Name, Versus> maps;

    private Result(Map<Name, Versus> maps) {
        this.maps = Map.copyOf(maps);
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
        if (!maps.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ABSENT_NAME_ERROR_MESSAGE_FORMAT, name.getName()));
        }
        return maps.get(name);
    }

    public int countDealerWin() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == Versus.LOSE)
                .count();
    }

    public int countDealerDraw() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == Versus.DRAW)
                .count();
    }

    public int countDealerLose() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == Versus.WIN)
                .count();
    }
}
