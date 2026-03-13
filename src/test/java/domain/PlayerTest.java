package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Denomination;
import blackjack.domain.judgement.GameResult;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Player;
import blackjack.domain.judgement.Status;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Trump;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Trump trump;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = new NoShuffleStrategy();
        trump = new Trump(strategy);
    }

    @Nested
    @DisplayName("승패 판정 테스트")
    class 승패_판정_테스트 {

        @Test
        @DisplayName("딜러 버스트, 플레이어 스테이")
        void 딜러_버스트_플레이어_스테이() {
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

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 둘 다 블랙잭인 경우")
        void 딜러_스테이_플레이어_스테이_둘_다_블랙잭인_경우() {
            Hand dealerHand = new Hand(List.of(
                    new Card(Suit.DIAMOND, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.ACE)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);

            Hand playerHand = new Hand(List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.DIAMOND, Denomination.ACE)));
            Player player = new Player(playerHand, Status.STAY, "pobi");

            GameResult expected = GameResult.DRAW;

            GameResult actual = player.calculateGameResult(dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 플레이어만 블랙잭인 경우")
        void 딜러_스테이_플레이어_스테이_플레이어만_블랙잭인_경우() {
            Hand dealerHand = new Hand(List.of(
                    new Card(Suit.DIAMOND, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.NINE)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);

            Hand playerHand = new Hand(List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.DIAMOND, Denomination.ACE)));
            Player player = new Player(playerHand, Status.STAY, "pobi");

            GameResult expected = GameResult.BLACKJACK;

            GameResult actual = player.calculateGameResult(dealer);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러 스테이, 플레이어 스테이: 딜러만 블랙잭인 경우")
        void 딜러_스테이_플레이어_스테이_딜러만_블랙잭인_경우() {
            Hand dealerHand = new Hand(List.of(
                    new Card(Suit.DIAMOND, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.ACE)));
            Dealer dealer = new Dealer(dealerHand, Status.STAY, trump);

            Hand playerHand = new Hand(List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.DIAMOND, Denomination.NINE)));
            Player player = new Player(playerHand, Status.STAY, "pobi");

            GameResult expected = GameResult.LOSE;

            GameResult actual = player.calculateGameResult(dealer);

            assertThat(actual).isEqualTo(expected);
        }
    }
}
