package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BustTest {

    private static final HankRank BUST = new Bust(22);

    @DisplayName("플레이어, 딜러 모두 버스트인 경우 딜러가 이긴다.")
    @Test
    void matchTest_whenPlayerAndDealerBust_DealerWin() {
        HankRank dealerRank = BUST;
        HankRank playerRank = BUST;

        assertThat(dealerRank.matchAtDealer(playerRank)).isEqualTo(SingleMatchResult.DEALER_WIN);
    }

    @DisplayName("딜러만 버스트인 경우, 플레이어가 이긴다.")
    @ParameterizedTest
    @MethodSource("normalRank")
    void matchTest_whenOnlyDealerBlackjack_PlayerWin(HankRank hankRank) {
        HankRank dealerRank = BUST;
        HankRank playerRank = hankRank;

        assertThat(dealerRank.matchAtDealer(playerRank)).isEqualTo(SingleMatchResult.PLAYER_WIN);
    }

    static Stream<HankRank> normalRank() {
        return Stream.of(new Stand(12), new Stand(20), new Stand(21));
    }

    @DisplayName("딜러만 버스트이고 플레이어가 블랙잭인 경우, 플레이어 블랙잭으로 승리한다.")
    @Test
    void matchTest_whenOnlyDealerBlackjack_PlayerBlackjackWin() {
        HankRank dealerRank = BUST;
        HankRank playerRank = new Blackjack();

        assertThat(dealerRank.matchAtDealer(playerRank)).isEqualTo(SingleMatchResult.PLAYER_BLACKJACK);
    }

    @DisplayName("해당 핸드 랭크는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {

        assertThat(BUST.isBlackjack()).isFalse();
    }

    @DisplayName("해당 핸드 랭크는 버스트이다.")
    @Test
    void isBustTest() {

        assertThat(BUST.isBust()).isTrue();
    }
}
