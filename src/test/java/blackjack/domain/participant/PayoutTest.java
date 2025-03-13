package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PayoutTest {

    @Nested
    @DisplayName(" 기능 테스트")
    class BetTest {

        @Test
        @DisplayName("베팅 금액을 추가할 수 있다")
        void addBetAmount() {
            Payout payout = new Payout(0);

            assertThat(payout.bet(1000)).isEqualTo(new Payout(1000));
        }

        @Test
        @DisplayName("0 이하라면 베팅 시 예외를 던진다")
        void throwExceptionIfUnderZero() {
            Payout payout = new Payout(0);

            assertThatThrownBy(() -> payout.bet(-1000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("베팅 금액은 0원 이상 입력해주세요.");
        }
    }

    @Nested
    @DisplayName("합 테스트")
    class AddPayoutTest {

        @Test
        @DisplayName("다른 Payout 객체를 받아 그 합을 구할 수 있다")
        void addPayout() {
            Payout payout = new Payout(500);
            Payout payoutValue1000 = new Payout(1000);

            assertThat(payout.add(payoutValue1000)).isEqualTo(new Payout(1500));
        }
    }

    @Nested
    @DisplayName("베팅 결과 테스트")
    class CalculatePayoutTest {

        Player player;
        Dealer dealer;
        Payout bet1000Payout;

        @BeforeEach
        void initParticipants() {
            player = new Player(new PlayerName("sana"));
            dealer = new Dealer();
            bet1000Payout = new Payout(1000);
        }

        @Test
        @DisplayName("블랙잭 vs 블랙잭이면 베팅 금액만큼 돌려받는다")
        void getPayoutBlackjackVsBlackjack() {
            List<Card> blackjackCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            player.addCards(blackjackCards1.get(0), blackjackCards1.get(1));

            List<Card> blackjackCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            dealer.addCards(blackjackCards2.get(0), blackjackCards2.get(1));

            assertThat(bet1000Payout.calculatePayout(dealer, player)).isEqualTo(new Payout(1000));
        }

        @Test
        @DisplayName("버스트 vs 버스트이면 플레이어는 모두 잃는다")
        void getPayoutBustVsBust() {
            List<Card> bustCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.THREE)
            );
            player.addCards(bustCards1.get(0), bustCards1.get(1), bustCards1.get(2));

            List<Card> bustCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.NINE),
                    new Card(Suit.CLUB, Denomination.FIVE)
            );
            dealer.addCards(bustCards2.get(0), bustCards2.get(1), bustCards2.get(2));

            assertThat(bet1000Payout.calculatePayout(dealer, player)).isEqualTo(new Payout(-1000));
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 베팅 금액의 1.5배를 받는다")
        void getPayoutPlayerWithBlackjack() {
            List<Card> blackjackCards = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            player.addCards(blackjackCards.get(0), blackjackCards.get(1));

            List<Card> generalCards = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.NINE),
                    new Card(Suit.CLUB, Denomination.TWO)
            );
            dealer.addCards(generalCards.get(0), generalCards.get(1), generalCards.get(2));

            assertThat(bet1000Payout.calculatePayout(dealer, player)).isEqualTo(new Payout(1500));
        }

        @Test
        @DisplayName("플레이어 승리 시, 베팅 금액만큼 받는다")
        void getPayoutPlayerWin() {
            List<Card> generalCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.NINE)
            );
            player.addCards(generalCards1.get(0), generalCards1.get(1));

            List<Card> generalCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.FIVE),
                    new Card(Suit.CLUB, Denomination.TWO)
            );
            dealer.addCards(generalCards2.get(0), generalCards2.get(1), generalCards2.get(2));

            assertThat(bet1000Payout.calculatePayout(dealer, player)).isEqualTo(new Payout(1000));
        }

        @Test
        @DisplayName("플레이어 승리 시, 베팅 금액만큼 받는다")
        void getPayoutPlayerLose() {
            List<Card> generalCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.FIVE),
                    new Card(Suit.CLUB, Denomination.TWO)
            );
            player.addCards(generalCards1.get(0), generalCards1.get(1), generalCards1.get(2));

            List<Card> generalCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.NINE)
            );
            dealer.addCards(generalCards2.get(0), generalCards2.get(1));

            assertThat(bet1000Payout.calculatePayout(dealer, player)).isEqualTo(new Payout(-1000));
        }
    }
}
