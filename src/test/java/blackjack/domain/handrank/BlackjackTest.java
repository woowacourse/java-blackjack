package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.fixture.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackTest {

    private final HandRank BLACKJACK = new Blackjack(HandFixture.BLACKJACK);

    @DisplayName("플레이어, 딜러 모두 블랙잭인 경우 비긴다.")
    @Test
    void matchTest_whenPlayerAndDealerBlackjack_matchDraw() {
        HandRank dealerRank = BLACKJACK;
        HandRank playerRank = BLACKJACK;

        assertThat(dealerRank.matchWithPlayer(playerRank)).isEqualTo(SingleMatchResult.DRAW);
    }

    @DisplayName("딜러만 블랙잭인 경우, 딜러가 이긴다.")
    @ParameterizedTest
    @MethodSource("notBlackjack")
    void matchTest_whenOnlyDealerBlackjack_dealerWin(HandRank handRank) {
        HandRank dealerRank = BLACKJACK;
        HandRank playerRank = handRank;

        assertThat(dealerRank.matchWithPlayer(playerRank)).isEqualTo(SingleMatchResult.DEALER_WIN);
    }

    static Stream<HandRank> notBlackjack() {
        return Stream.of(
                new Stand(HandFixture.CARDS_SCORE_16),
                new Stand(HandFixture.CARDS_SCORE_17),
                new Bust(HandFixture.BUSTED)
        );
    }
}
