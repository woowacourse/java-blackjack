import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    public static Stream<Arguments> needsToHit() {
        return Stream.of(
                Arguments.of(new BlackjackHand(TestDefaults.getCardsByRanks(List.of(Rank.TEN,Rank.SIX))),true),
                Arguments.of(new BlackjackHand(TestDefaults.getCardsByRanks(List.of(Rank.TEN,Rank.SEVEN))),false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("16이하의 값이라면 딜러는 카드를 더 받는다.")
    void needsToHit(BlackjackHand blackjackHand, boolean expected) {
        Dealer dealer = new Dealer(blackjackHand);

        assertThat(dealer.needsToHit()).isEqualTo(expected);
    }
}
