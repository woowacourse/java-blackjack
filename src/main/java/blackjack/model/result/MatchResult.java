package blackjack.model.result;

import blackjack.model.player.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
/*
    WIN("승", (dealer, gamer) ->
            dealer.isBlackjack() && !gamer.isBlackjack() || !dealer.isBust() && gamer.isBust()
                    || !dealer.isBust() && dealer.isWinBy(gamer)
    ),
    LOSE("패", (dealer, gamer) ->
            dealer.isBust() && !gamer.isBust() || !dealer.isBlackjack() && gamer.isBlackjack()
                    || !dealer.isBust() && !dealer.isWinBy(gamer)),
    DRAW("무", (dealer, gamer) ->
            dealer.isBust() && gamer.isBust() || dealer.isBlackjack() && dealer.isBlackjack()
                    || !dealer.isBust() && dealer.isDrawWith(gamer)),
    ;

    private final String value;
    private final BiPredicate<Participant, Participant> biPredicate;

    MatchResult(String value, BiPredicate<Participant, Participant> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static MatchResult findBy(Participant dealer, Participant gamer) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.biPredicate.test(dealer, gamer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 값이 없습니다."));
    }

    public String getValue() {
        return value;
    }

    public String getReversValue() {
        if (value.equals(MatchResult.WIN.value)) {
            return MatchResult.LOSE.value;
        }
        if (value.equals(MatchResult.LOSE.value)) {
            return MatchResult.WIN.value;
        }
        return MatchResult.DRAW.value;
    }

 */
}
