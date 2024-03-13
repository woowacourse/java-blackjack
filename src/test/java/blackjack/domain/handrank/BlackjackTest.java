package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackTest {

    private final HankRank BLACKJACK = new Blackjack();

    @DisplayName("플레이어, 딜러 모두 블랙잭인 경우 비긴다.")
    @Test
    void matchTest_whenPlayerAndDealerBlackjack_matchDraw() {
        HankRank dealerRank = BLACKJACK;
        HankRank playerRank = BLACKJACK;

        assertThat(dealerRank.matchAtDealer(playerRank)).isEqualTo(SingleMatchResult.DRAW);
    }

    @DisplayName("딜러만 블랙잭인 경우, 딜러가 이긴다.")
    @ParameterizedTest
    @MethodSource("notBlackjack")
    void matchTest_whenOnlyDealerBlackjack_dealerWin(HankRank hankRank) {
        HankRank dealerRank = BLACKJACK;
        HankRank playerRank = hankRank;

        assertThat(dealerRank.matchAtDealer(playerRank)).isEqualTo(SingleMatchResult.DEALER_WIN);
    }

    static Stream<HankRank> notBlackjack() {
        return Stream.of(new NormalRank(12), new NormalRank(20), new Bust());
    }

    @DisplayName("해당 핸드 랭크는 블랙잭이다.")
    @Test
    void isBlackjackTest() {

        assertThat(BLACKJACK.isBlackjack()).isTrue();
    }

    @DisplayName("해당 핸드 랭크는 버스트가 아니다.")
    @Test
    void isBustTest() {

        assertThat(BLACKJACK.isBust()).isFalse();
    }
}
