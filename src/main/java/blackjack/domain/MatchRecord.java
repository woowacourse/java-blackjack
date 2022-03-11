package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public enum MatchRecord {

    WIN("승", (player, dealer) -> (dealer.isBust() && !player.isBust()) || player.isWinTo(dealer)),
    TIE("무", (player, dealer) -> !player.isWinTo(dealer) && !dealer.isWinTo(player)),
    LOSS("패", (player, dealer) -> player.isBust() || dealer.isWinTo(player));

    private final String name;
    private final BiPredicate<Player, Dealer> biPredicate;

    MatchRecord(String name, BiPredicate<Player, Dealer> biPredicate) {
        this.name = name;
        this.biPredicate = biPredicate;
    }

    public static MatchRecord findMatchRecord(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public MatchRecord reverseResult() {
        if (this == MatchRecord.LOSS) {
            return MatchRecord.WIN;
        }
        if (this == MatchRecord.WIN) {
            return MatchRecord.LOSS;
        }
        return MatchRecord.TIE;
    }

    public String getName() {
        return name;
    }
}
