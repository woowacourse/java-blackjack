package blackjack.model;

import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    /*
    WIN("승", (dealer, gamer) ->
            dealer.isBlackJack() && !gamer.isBlackJack() || !dealer.isBust() && gamer.isBust()
                    || !dealer.isBust() && dealer.isWinBy(gamer)
    ),
    LOSE("패", (dealer, gamer) ->
            dealer.isBust() && !gamer.isBust() || !dealer.isBlackJack() && gamer.isBlackJack()
                    || !dealer.isBust() && !dealer.isWinBy(gamer)),
    DRAW("무", (dealer, gamer) ->
            dealer.isBust() && gamer.isBust() || dealer.isBlackJack() && dealer.isBlackJack()
                    || !dealer.isBust() && dealer.isDrawWith(gamer)),
    ;

    private final String value;
    private final BiPredicate<Player, Player> biPredicate;

    MatchResult(String value, BiPredicate<Player, Player> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static MatchResult find(Player dealer, Player gamer) {
        return Arrays.stream(values())
                .filter(result -> result.biPredicate.test(dealer, gamer))
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
