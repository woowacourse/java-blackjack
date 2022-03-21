package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
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

class BettingReturnTest {

    @Test
    @DisplayName("RevenueResult 는 불변 객체다")
    void revenueResult_is_immutable() {
        // given
        Dealer dealer = createDealerWithDenominations(TEN, QUEEN);

        Player player = playerBuilder()
                .denominations(ACE, JACK)
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, List.of(player));

        // when & then
        assertThatThrownBy(() -> bettingReturn.getPlayersReturn().remove(player.getName()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러는 아닐 때 플레이어의 수익은 1.5 배이다")
    void player_blackjack_when_player_blackjack_and_dealer_is_not() {
        // given
        Dealer dealer = createDealerWithDenominations(TEN, QUEEN);

        Player player = playerBuilder()
                .denominations(ACE, JACK)
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, List.of(player));

        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(15_000),
                () -> assertThat(dealerReturn).isEqualTo(-15_000)
        );
    }

    @Test
    @DisplayName("플레이어와 플레이어 모두 블랙잭일 때 무승부이기에 플레이어의 수익률은 0 이다")
    void player_draw_when_player_blackjack_and_dealer_is_not() {
        // given
        Dealer dealer = createDealerWithDenominations(ACE, TEN);

        Player player = playerBuilder()
                .denominations(ACE, JACK)
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, List.of(player));

        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(0),
                () -> assertThat(dealerReturn).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("플레이어가 딜러를 이겼을 때 플레이어의 수익률은 1 이다")
    void player_win() {
        // given
        Dealer dealer = createDealerWithDenominations(TWO, THREE); // 5

        Player player = playerBuilder()
                .denominations(FOUR, FIVE) // 9
                .bettingAmount(10_000)
                .build();


        BettingReturn bettingReturn = BettingReturn.of(dealer, player);

        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(10_000),
                () -> assertThat(dealerReturn).isEqualTo(-10_000)
        );
    }

    @Test
    @DisplayName("플레이어가 딜러에게 졌을 때 플레이어의 수익률은 -1 이다")
    void player_lose() {
        // given
        Dealer dealer = createDealerWithDenominations(NINE, TEN);

        Player player = playerBuilder()
                .denominations(FIVE, EIGHT)
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, player);

        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(-10_000),
                () -> assertThat(dealerReturn).isEqualTo(10_000)
        );
    }

    @Test
    @DisplayName("플레이어가 딜러와 비겼을 때 플레이어의 수익률은 0 이다")
    void player_draw() {
        // given
        Dealer dealer = createDealerWithDenominations(THREE, NINE); // 12

        Player player = playerBuilder()
                .denominations(FOUR, EIGHT) // 12
                .bettingAmount(10_000)
                .build();

        BettingReturn bettingReturn = BettingReturn.of(dealer, player);

        int playerReturn = bettingReturn.findPlayerReturn(player.getName());
        int dealerReturn = bettingReturn.getDealerReturn();

        // when & then
        assertAll(
                () -> assertThat(playerReturn).isEqualTo(0),
                () -> assertThat(dealerReturn).isEqualTo(0)
        );
    }

    @Nested
    @DisplayName("딜러가 버스트일 때")
    class DealerIsBust {

        private final Dealer dealer = createDealerWithDenominations(TEN, NINE, THREE);

        @Test
        @DisplayName("플레이어가 블랙잭 플레이어의 수익률은 1.5이다")
        void player_21_then_1() {
            // given
            Player player = playerBuilder()
                    .denominations(ACE, JACK)
                    .bettingAmount(10_000)
                    .build();

            BettingReturn bettingReturn = BettingReturn.of(dealer, player);

            int playerReturn = bettingReturn.findPlayerReturn(player.getName());
            int dealerReturn = bettingReturn.getDealerReturn();

            // when & then
            assertAll(
                    () -> assertThat(playerReturn).isEqualTo(15_000),
                    () -> assertThat(dealerReturn).isEqualTo(-15_000)
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

            BettingReturn bettingReturn = BettingReturn.of(dealer, player);

            int playerReturn = bettingReturn.findPlayerReturn(player.getName());
            int dealerReturn = bettingReturn.getDealerReturn();

            // when & then
            assertAll(
                    () -> assertThat(playerReturn).isEqualTo(10_000),
                    () -> assertThat(dealerReturn).isEqualTo(-10_000)
            );
        }

        @Test
        @DisplayName("플레이어 또한 버스트라면 플레이어의 수익률은 -1이다")
        void also_player_bust_then_1() {

            // given
            Player player = playerBuilder()
                    .denominations(JACK, QUEEN, TWO)
                    .bettingAmount(10_000)
                    .build();

            BettingReturn bettingReturn = BettingReturn.of(dealer, player);

            int playerReturn = bettingReturn.findPlayerReturn(player.getName());
            int dealerReturn = bettingReturn.getDealerReturn();

            // when & then
            assertAll(
                    () -> assertThat(playerReturn).isEqualTo(-10_000),
                    () -> assertThat(dealerReturn).isEqualTo(10_000)
            );
        }
    }

}
