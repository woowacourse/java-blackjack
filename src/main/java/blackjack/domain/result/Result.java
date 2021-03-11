package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승", Player::playerBlackJack, Player::winCondition),
    LOSE("패", Player::dealerBlackJack, Player::lossCondition),
    DRAW("무", Player::bothBlackJack, Player::drawCondition);

    private final String value;
    private final BiPredicate<Player, Dealer> blackJackCheck;
    private final BiPredicate<Player, Dealer> scoreCheck;

    Result(String value, final BiPredicate<Player, Dealer> blackJackCheck, final BiPredicate<Player, Dealer> scoreCheck) {
        this.value = value;
        this.blackJackCheck = blackJackCheck;
        this.scoreCheck = scoreCheck;
    }

    public static Result getPlayerResult(final Player player, final Dealer dealer) {
        return Arrays.stream(Result.values())
                .filter(result -> result.blackJackCheck.test(player, dealer))
                .findAny()
                .orElseGet(() ->
                        Arrays.stream(Result.values())
                                .filter(result -> result.scoreCheck.test(player, dealer))
                                .findAny()
                                .orElseThrow(() -> new RuntimeException("승패를 가리지 못합니다."))
                );
    }

    public String getResultAsString() {
        return this.value;
    }
}
