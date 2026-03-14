package domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.Hand;
import domain.card.vo.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    private final Dealer dealer = TestFixture.createDefaultDealer();

    public static Stream<Arguments> canDraw() {
        return Stream.of(
                Arguments.of(TestFixture.createHandByRank(List.of(Rank.TEN, Rank.SIX)), true),
                Arguments.of(TestFixture.createHandByRank(List.of(Rank.TEN, Rank.SEVEN)), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("canDraw(): 16 이하이면서 Running State 라면 True를 리턴한다.")
    void canDraw(Hand hand, boolean expected) {
        dealer.startState(TestFixture.getDefaultStateGenerator(), hand);
        assertThat(dealer.canDraw()).isEqualTo(expected);
    }


}
