package result;

import participant.Dealer;
import participant.Player;

import java.util.Arrays;

public enum MatchResultType {

    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final int condition;

    MatchResultType(int condition) {
        this.condition = condition;
    }

    public static MatchResultType of(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return Arrays.stream(MatchResultType.values())
                .filter(result -> result.condition == player.calculateScore().compareTo(dealer.calculateScore()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 결과 입니다"));
    }

    public MatchResultType calculateReverseResult() {
        return Arrays.stream(MatchResultType.values())
                .filter(result -> result.condition == condition * -1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("반대되는 결과가 존재하지 않습니다"));
    }
}
