package blackjack.model.result;

import blackjack.model.player.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    WIN("승", (dealer, player) ->
            dealer.isBlackjack() && !player.isBlackjack() ||
                    !dealer.isBust() && player.isBust() ||
                    dealer.isBust() && player.isBust() ||
                    !dealer.isBust() && !player.isBust() && dealer.isWinBy(player)),

    LOSE("패", (dealer, player) ->
            !dealer.isBlackjack() && player.isBlackjack() ||
                    dealer.isBust() && !player.isBust() ||
                    !dealer.isBust() && !player.isBust() && dealer.isLoseBy(player)),

    DRAW("무", (dealer, player) ->
            dealer.isBlackjack() && player.isBlackjack() ||
                    !dealer.isBust() && !player.isBust() && dealer.isDrawWith(player)),
    ;

    private final String value;
    private final BiPredicate<Participant, Participant> biPredicate;

    MatchResult(String value, BiPredicate<Participant, Participant> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static MatchResult match(Participant dealer, Participant gamer) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.biPredicate.test(dealer, gamer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 값이 없습니다."));
    }

    public String get() {
        return value;
    }

    public String getReverse() {
        if (value.equals(WIN.get())) {
            return LOSE.value;
        }
        if (value.equals(LOSE.get())) {
            return WIN.value;
        }
        return DRAW.value;
    }
}
