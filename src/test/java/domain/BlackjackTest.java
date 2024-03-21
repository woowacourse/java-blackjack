package domain;

import dto.ParticipantsResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("딜러가 히트 했을 때 점수의 합이 17 이상인지 확인한다")
    void dealerHit() {
        final List<String> names = List.of("a", "b", "c");
        final List<Integer> betAmounts = List.of(100, 200, 300);
        final Blackjack blackjack = Blackjack.startWithInitialization(names, betAmounts);

        blackjack.dealerHit(() -> {
        });

        final int dealerScore = ParticipantsResponse.of(blackjack).dealerResponse().score();
        Assertions.assertThat(dealerScore).isGreaterThanOrEqualTo(17);
    }

    @Test
    @DisplayName("플레이어는 점수가 21미만이라면 항상 카드를 받을 수 있다")
    void playersHit() {
        final List<String> names = List.of("a", "b", "c");
        final List<Integer> betAmounts = List.of(100, 200, 300);
        final Blackjack blackjack = Blackjack.startWithInitialization(names, betAmounts);

        blackjack.playersHit((a) -> true, (a, b) -> {
        });

        final int score = ParticipantsResponse.of(blackjack).playerResponse().get(0).score();
        Assertions.assertThat(score).isGreaterThanOrEqualTo(21);
    }
}
