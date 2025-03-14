package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinLossResultTest {


    @Nested
    @DisplayName("승무패 결과 계산 테스트")
    class CalculateWinLossResultTest {

        @Test
        @DisplayName("플레이어 승리")
        void test1() {
            // given
            Player player = new Player("띠용");
            Dealer dealer = new Dealer();

            // when
            player.addCard(new Card(Denomination.TEN, Suit.SPADE));
            player.addCard(new Card(Denomination.JACK, Suit.SPADE));
            dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
            dealer.addCard(new Card(Denomination.SEVEN, Suit.HEART));

            // then
            assertThat(WinLossResult.computeWinLoss(player, dealer)).isEqualTo(WinLossResult.WIN);
        }

        @Test
        @DisplayName("플레이어 패배")
        void test2() {
            // given
            Player player = new Player("띠용");
            Dealer dealer = new Dealer();

            // when
            player.addCard(new Card(Denomination.TEN, Suit.SPADE));
            player.addCard(new Card(Denomination.SIX, Suit.SPADE));
            dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
            dealer.addCard(new Card(Denomination.SEVEN, Suit.HEART));

            // then
            assertThat(WinLossResult.computeWinLoss(player, dealer)).isEqualTo(WinLossResult.LOSS);
        }

        @Test
        @DisplayName("무승부")
        void test3() {
            // given
            Player player = new Player("띠용");
            Dealer dealer = new Dealer();

            // when
            player.addCard(new Card(Denomination.TEN, Suit.SPADE));
            player.addCard(new Card(Denomination.JACK, Suit.SPADE));
            dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
            dealer.addCard(new Card(Denomination.QUEEN, Suit.HEART));

            // then
            assertThat(WinLossResult.computeWinLoss(player, dealer)).isEqualTo(WinLossResult.DRAW);
        }

        @Test
        @DisplayName("플레이어 블랙잭 승리")
        void test4() {
            // given
            Player player = new Player("띠용");
            Dealer dealer = new Dealer();

            // when
            player.addCard(new Ace(Suit.CLUB));
            player.addCard(new Card(Denomination.JACK, Suit.SPADE));
            dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
            dealer.addCard(new Card(Denomination.SEVEN, Suit.HEART));

            // then
            assertThat(WinLossResult.computeWinLoss(player, dealer)).isEqualTo(WinLossResult.WIN_WITH_BLACK_JACK);
        }
    }


    @Test
    @DisplayName("승무패 반환 테스트")
    void test1() {
        assertAll(() -> assertThat(WinLossResult.of(1)).isEqualTo(WinLossResult.WIN),
                () -> assertThat(WinLossResult.of(-1)).isEqualTo(WinLossResult.LOSS),
                () -> assertThat(WinLossResult.of(0)).isEqualTo(WinLossResult.DRAW),
                () -> assertThat(WinLossResult.of(2)).isEqualTo(WinLossResult.WIN_WITH_BLACK_JACK)
        );

    }
}