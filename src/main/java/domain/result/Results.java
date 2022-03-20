package domain.result;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.Map;

public class Results {

    private static final String ABSENT_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름이 존재하지 않습니다.";
    private static final String NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] 딜러가 BlackJack 이 아닙니다.";

    private final Map<Name, WinOrLose> maps;

    private Results(Map<Name, WinOrLose> maps) {
        this.maps = Map.copyOf(maps);
    }

    public static Results generateResultAtDealerBlackJack(Dealer dealer, Players players) {
        validateDealerIsBlackJack(dealer);
        return new Results(players.compareAtDealerBlackJack(dealer));
    }

    private static void validateDealerIsBlackJack(Dealer dealer) {
        if (!dealer.isBlackJack()) {
            throw new IllegalStateException(NOT_DEALER_BLACK_JACK_SITUATION_ERROR_MESSAGE);
        }
    }

    public static Results generateResultAtFinal(Dealer dealer, Players players) {
        return new Results(players.compareResultAtFinal(dealer));
    }

    public WinOrLose getVersusOfPlayer(Name name) {
        if (!maps.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ABSENT_NAME_ERROR_MESSAGE_FORMAT, name.getName()));
        }
        return maps.get(name);
    }

    public int countDealerWin() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == WinOrLose.LOSE)
                .count();
    }

    public int countDealerDraw() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == WinOrLose.DRAW)
                .count();
    }

    public int countDealerLose() {
        return (int) maps.keySet().stream()
                .filter(key -> maps.get(key) == WinOrLose.WIN)
                .count();
    }
}
