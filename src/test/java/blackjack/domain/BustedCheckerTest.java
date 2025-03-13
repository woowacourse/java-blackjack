package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.utils.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BustedCheckerTest {

    public static Stream<Arguments> bustedCheckTest() {
        return Stream.of(
                Arguments.of(HandFixture.busted(), HandFixture.busted(), BustedChecker.ALL_BUSTED, true),
                Arguments.of(HandFixture.busted(), HandFixture.normal(), BustedChecker.DEALER_BUSTED, true),
                Arguments.of(HandFixture.normal(), HandFixture.busted(), BustedChecker.PLAYER_BUSTED, true),
                Arguments.of(HandFixture.normal(), HandFixture.normal(), BustedChecker.NONE, true)
        );
    }

    @DisplayName("ALL_BUSTED는 플레이어 딜러 모두 busted라면 true를 반환한다.")
    @MethodSource("bustedCheckTest")
    @ParameterizedTest
    void test1(Hand dealerHand, Hand hand, BustedChecker bustedChecker, boolean expect) {
        // given
        Dealer dealer = new Dealer(dealerHand);

        PlayerHand playerHand = new PlayerHand(hand, Wallet.bet(1000));
        Player player = new Player("꾹이", playerHand);

        // when & then
        assertThat(bustedChecker.check(player, dealer)).isEqualTo(expect);
    }
}
