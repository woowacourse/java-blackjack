package blackjack.domain.result;

import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CardScoreResult {

    LOSE("패", (player, dealer) -> player.isBust() || dealer.isWin(player)
        || (!player.isBlackjack() && dealer.isBlackjack())),
    WIN("승", ((player, dealer) -> (!player.isBust() && dealer.isBust()) || player.isWin(dealer) || (
        player.isBlackjack() && !dealer.isBlackjack()))),
    DRAW("무", (player, dealer) -> !player.isWin(dealer) && !dealer.isWin(player));

    private final String name;
    private final BiPredicate<Participant, Participant> biPredicate;

    CardScoreResult(String name,
        BiPredicate<Participant, Participant> biPredicate) {
        this.name = name;
        this.biPredicate = biPredicate;
    }

    public static CardScoreResult findCardScoreResult(Participant player, Participant dealer) {
        return Arrays.stream(values())
            .filter(value -> value.biPredicate.test(player, dealer))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 결과를 찾을 수 없습니다."));
    }

    public CardScoreResult reverse() {
        if (LOSE == this) {
            return WIN;
        }
        if (WIN == this) {
            return LOSE;
        }
        return this;
    }

    public String getName() {
        return name;
    }
}
