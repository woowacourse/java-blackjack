package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.utils.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameResultTypePlayerJudgementTest {

    public static Stream<Arguments> typeJudgeArgument() {
        return Stream.of(
                Arguments.of(HandFixture.createHandWithOptimisticValue20(),
                        HandFixture.createHandWithOptimisticValue15(), GameResultType.WIN),
                Arguments.of(HandFixture.normal(), HandFixture.busted(), GameResultType.WIN),
                Arguments.of(HandFixture.createHandWithOptimisticValue20(),
                        HandFixture.createHandWithOptimisticValue20(), GameResultType.TIE),
                Arguments.of(HandFixture.busted(), HandFixture.busted(), GameResultType.LOSE),
                Arguments.of(HandFixture.createHandWithOptimisticValue15(),
                        HandFixture.createHandWithOptimisticValue20(), GameResultType.LOSE),
                Arguments.of(HandFixture.busted(), HandFixture.normal(), GameResultType.LOSE)
        );
    }

    @DisplayName("상황에 따라 맞는 승패 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("typeJudgeArgument")
    void test1(Hand playerHand, Hand delerHand, GameResultType expect) {
        PlayerHand hand = new PlayerHand(playerHand, Wallet.bet(1000));

        Player player = new Player("꾹이", hand);
        Dealer dealer = new Dealer(delerHand);

        assertThat(GameResultTypePlayerJudgement.find(player, dealer)).isEqualTo(expect);
    }

}
