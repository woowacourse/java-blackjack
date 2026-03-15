import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Rank;
import domain.player.Dealer;
import domain.player.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    public static Stream<Arguments> needsToHit() {
        return Stream.of(
                Arguments.of(new Hand(TestDefaults.getCardsByRanks(List.of(Rank.TEN, Rank.SIX))), true),
                Arguments.of(new Hand(TestDefaults.getCardsByRanks(List.of(Rank.TEN, Rank.SEVEN))), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("16이하의 값이라면 딜러는 카드를 더 받는다.")
    void needsToHit(Hand hand, boolean expected) {
        Dealer dealer = Dealer.from(hand);

        assertThat(dealer.needsToHit()).isEqualTo(expected);
    }
}
