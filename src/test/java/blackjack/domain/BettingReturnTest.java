package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.dealerBuilder;
import static blackjack.utils.ParticipantsCreationUtils.playerBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("[플레이어 수익]")
class BettingReturnTest {

    private void assertBettingReturn(Dealer dealer,
                                     int dealerBettingReturnExpected,
                                     Player player,
                                     int playerBettingReturnExpected) {

        BettingReturn bettingReturn = BettingReturn.of(dealer, List.of(player));
        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(playerBettingReturnExpected),
                () -> assertThat(dealerReturn).isEqualTo(dealerBettingReturnExpected)
        );
    }

    @Test
    @DisplayName("RevenueResult 는 불변 객체다")
    void revenueResult_is_immutable() {
        // given
        Dealer dealer = dealerBuilder()
                .denominations(TEN, QUEEN)
                .build();

        Player player = playerBuilder()
                .denominations(ACE, JACK)
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, List.of(player));

        // when & then
        assertThatThrownBy(() -> bettingReturn.getPlayersReturn().remove(player.getName()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Nested
    @DisplayName("딜러가 21점 미만일 때")
    class DealerUnder21 {

        Dealer dealer = dealerBuilder()
                .denominations(FOUR, EIGHT) // 12
                .build();

        @Test
        @DisplayName("플레이어가 블랙잭이면 수익률은 1.5 이다 (승)")
        void when_dealer_under_21_player_blackjack_then_1_point_5() {

            Player player = playerBuilder()
                    .denominations(ACE, JACK)
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, -15_000,
                    player, +15_000
            );
        }

        @Test
        @DisplayName("플레이어가 딜러의 점수보다 높으면서 카드합이 21이지만 3장인 경우 블랙잭이 아닌 그냥 승이다 (수익률 1)")
        void when_dealer_under_21_and_player_score_21_but_3cards_then_not_blackjack_win_but_win() {

            Player player = playerBuilder()
                    .denominations(ACE, JACK, QUEEN)  // 21
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, -10_000,
                    player, +10_000
            );
        }

        @Test
        @DisplayName("플레이어가 버스트라면 수익률은 -1 이다")
        void when_dealer_under_21_player_bust_then_minus_1() {

            Player player = playerBuilder()
                    .denominations(FIVE, NINE, QUEEN) // 22
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, +10_000,
                    player, -10_000
            );
        }

        @Test
        @DisplayName("플레이어가 딜러보다 점수가 높다면 수익률은 1이다")
        void when_dealer_under_21_and_player_is_more_high_score_then_1() {

            Player player = playerBuilder()
                    .denominations(FIVE, TEN) // 22
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, -10_000,
                    player, +10_000
            );
        }

        @Test
        @DisplayName("딜러가 플레이어보다 점수가 높다면 수익률은 -1이다")
        void when_dealer_under_21_and_dealer_is_more_high_score_then_1() {

            Player player = playerBuilder()
                    .denominations(THREE, SEVEN) // 110
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, +10_000,
                    player, -10_000
            );
        }

        @Test
        @DisplayName("플레이어와 딜러가 동점이라면 무승부이다 (수익률 0)")
        void when_dealer_under_21_and_same_score_then_0() {

            Player player = playerBuilder()
                    .denominations(THREE, NINE)
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, 0,
                    player, 0
            );
        }
    }

    @Nested
    @DisplayName("딜러가 블랙잭일 때")
    class DealerIsBlackjack {

        private final Dealer dealer = dealerBuilder()
                .denominations(ACE, TEN) // 21
                .build();

        @Test
        @DisplayName("플레이어 또한 블랙잭이라면 수익률은 0이다 (무승부)")
        void when_all_blackjack_then_0() {

            Player player = playerBuilder()
                    .denominations(ACE, JACK) // 21
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, 0,
                    player, 0
            );
        }

        @Test
        @DisplayName("플레이어 점수가 20 이하면 수익률은 -1 이다 (패)")
        void when_dealer_blackjack_player_under_21_then_minus_1() {

            Player player = playerBuilder()
                    .denominations(QUEEN, JACK) // 21
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, +10_000,
                    player, -10_000
            );
        }

        @Test
        @DisplayName("플레이어가 버스트이면 수익률은 -1 이다 (패)")
        void when_dealer_blackjack_player_bust_then_minus_1() {

            Player player = playerBuilder()
                    .denominations(QUEEN, JACK, TWO) // 21
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, +10_000,
                    player, -10_000
            );
        }
    }

    @Nested
    @DisplayName("딜러가 버스트일 때")
    class DealerIsBust {

        private final Dealer dealer = dealerBuilder()
                .denominations(TEN, NINE, THREE) // 22
                .build();

        @Test
        @DisplayName("플레이어가 블랙잭 플레이어의 수익률은 1.5이다")
        void player_21_then_1() {
            // given
            Player player = playerBuilder()
                    .denominations(ACE, JACK)
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, -15_000,
                    player, +15_000
            );
        }

        @Test
        @DisplayName("플레이어의 점수가 20이면 플레이어의 수익률은 1이다")
        void player_20_then_1() {
            // given
            Player player = playerBuilder()
                    .denominations(JACK, EIGHT, TWO)
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, -10_000,
                    player, +10_000
            );
        }

        @Test
        @DisplayName("플레이어 또한 버스트라면 플레이어의 수익률은 -1이다")
        void also_player_bust_then_1() {
            // given
            Player player = playerBuilder()
                    .denominations(JACK, QUEEN, TWO) // 22
                    .bettingAmount(10_000)
                    .build();

            assertBettingReturn(
                    dealer, +10_000,
                    player, -10_000
            );
        }
    }

}
