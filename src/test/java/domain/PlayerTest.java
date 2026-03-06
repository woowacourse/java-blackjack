package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Denomination;
import blackjack.domain.GameResult;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Status;
import blackjack.domain.Suit;
import blackjack.domain.Trump;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4~10자인 경우")
    void 정상_테스트_1() {
        assertDoesNotThrow(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"));
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4자 미만인 경우")
    void 예외_테스트_1() {
        assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pob"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 10자 초과인 경우")
    void 예외_테스트_2() {
        assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "jasonjasonj"))
            .isInstanceOf(IllegalArgumentException.class);
    }

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

            GameResult actual = player.calculateGameResult(dealer);

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

            GameResult actual = player.calculateGameResult(dealer);

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

            GameResult actual = player.calculateGameResult(dealer);

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

            GameResult actual = player.calculateGameResult(dealer);

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

            GameResult actual = player.calculateGameResult(dealer);

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

            GameResult actual = player.calculateGameResult(dealer);

            assertThat(actual).isEqualTo(expected);
        }
    }
}
