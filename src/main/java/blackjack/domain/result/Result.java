package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    BLACKJACK_WIN("블랙잭승", Player::blackJackWinCondition, 2.5),
    WIN("승", Player::winCondition, 2.0),
    LOSE("패", Player::lossCondition, -1),
    DRAW("무", Player::drawCondition, 1);

    private final String value;
    private final BiPredicate<Player, Dealer> matchCondition;
    private final double rate;

    Result(String value, final BiPredicate<Player, Dealer> matchCondition, double rate) {
        this.value = value;
        this.matchCondition = matchCondition;
        this.rate = rate;
    }

    public static Result evaluate(final Player player, final Dealer dealer) {
        return Arrays.stream(Result.values())
                .filter(result -> result.matchCondition.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new RuntimeException("승패를 가리지 못합니다."));
    }

    public String getResultAsString() {
        return this.value;
    }

    public double getRate() {
        return this.rate;
    }
}
