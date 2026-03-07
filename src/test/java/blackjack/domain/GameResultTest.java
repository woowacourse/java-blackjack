package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("승패 판정 테스트")
    class 승패_판정_테스트 {

        @Test
        @DisplayName("딜러 버스트, 플레이어 스테이")
        void 딜러_버스트_플레이어_스테이() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.DIAMOND, Denomination.KING)));
            Dealer dealer = new Dealer(dealerHand, Status.BURST, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX)));
            Player player = new Player(playerHand, Status.STAY, "pobi");
            GameResult expected = GameResult.WIN;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 버스트, 플레이어 버스트")
        void 딜러_버스트_플레이어_버스트() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.DIAMOND, Denomination.KING)));
            Dealer dealer = new Dealer(dealerHand, Status.BURST, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.DIAMOND, Denomination.KING)));
            Player player = new Player(playerHand, Status.BURST, "pobi");
            GameResult expected = GameResult.LOSE;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 버스트")
        void 딜러_스테이_플레이어_버스트() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.TEN)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.DIAMOND, Denomination.KING)));
            Player player = new Player(playerHand, Status.BURST, "pobi");
            GameResult expected = GameResult.LOSE;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 플레이어 점수가 더 높은 경우")
        void 딜러_스테이_플레이어_스테이_플레이어_점수가_더_높은_경우() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SEVEN)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.TEN)));
            Player player = new Player(playerHand, Status.STAY, "pobi");
            GameResult expected = GameResult.WIN;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 플레이어 점수가 딜러와 같은 경우")
        void 딜러_스테이_플레이어_스테이_플레이어_점수가_딜러와_같은_경우() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SEVEN)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SEVEN)));
            Player player = new Player(playerHand, Status.STAY, "pobi");
            GameResult expected = GameResult.DRAW;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 플레이어 점수가 더 낮은 경우")
        void 딜러_스테이_플레이어_스테이_플레이어_점수가_더_낮은_경우() {
            Trump trump = new Trump();
            Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SEVEN)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);
            Hand playerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.SIX)));
            Player player = new Player(playerHand, Status.STAY, "pobi");
            GameResult expected = GameResult.LOSE;

            GameResult actual = GameResult.calculate(player, dealer);

            assertThat(actual).isEqualTo(expected);
        }
    }
}