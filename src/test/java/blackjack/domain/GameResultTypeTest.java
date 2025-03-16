package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.utils.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTypeTest {

    public static Stream<Arguments> gameResultTypeArguments() {
        return Stream.of(Arguments.of(HandFixture.blackjack(), HandFixture.normal(), GameResultType.WIN),
                Arguments.of(HandFixture.normal(), HandFixture.blackjack(), GameResultType.LOSE),
                Arguments.of(HandFixture.blackjack(), HandFixture.blackjack(), GameResultType.PUSH),
                Arguments.of(HandFixture.blackjack(), HandFixture.createHandWithOptimisticValue21(),
                        GameResultType.WIN));
    }

    @DisplayName("딜러와 플레이어의 카드에 따라 승패가 반환된다.")
    @ParameterizedTest
    @MethodSource("gameResultTypeArguments")
    void test2(Hand hand, Hand delerHand, GameResultType expect) {
        PlayerHand playerHand = new PlayerHand(hand, Wallet.bet(10));

        Player player = new Player("꾹이", playerHand);
        Dealer dealer = new Dealer(delerHand);

        assertThat(GameResultType.judgeForPlayer(player, dealer)).isEqualTo(expect);
    }
}
