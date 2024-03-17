package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackTest {

    private final HankRank BLACKJACK = new Blackjack();

    @DisplayName("플레이어, 딜러 모두 블랙잭인 경우 비긴다.")
    @Test
    void matchTest_whenPlayerAndDealerBlackjack_matchDraw() {
        HankRank dealerRank = BLACKJACK;
        HankRank playerRank = BLACKJACK;

        assertThat(dealerRank.competeWithPlayer(playerRank)).isEqualTo(SingleMatchResult.DRAW);
    }

    @DisplayName("딜러만 블랙잭인 경우, 딜러가 이긴다.")
    @ParameterizedTest
    @MethodSource("notBlackjack")
    void matchTest_whenOnlyDealerBlackjack_dealerWin(HankRank hankRank) {
        HankRank dealerRank = BLACKJACK;
        HankRank playerRank = hankRank;

        assertThat(dealerRank.competeWithPlayer(playerRank)).isEqualTo(SingleMatchResult.DEALER_WIN);
    }

    static Stream<HankRank> notBlackjack() {
        return Stream.of(new Stand(12), new Stand(20), new Bust(22));
    }
}
