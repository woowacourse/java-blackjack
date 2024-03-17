package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.fixture.CardsFixture;
import blackjack.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BettingPlayerTest {

    private static final Money DEFAULT_BET = new Money(10_000);

    @DisplayName("딜러와 플레이어의 손패에 따라 자신의 상금을 계산할 수 있다.")
    @Nested
    class calculatePrizeTest {
        @DisplayName("플레이어가 블랙잭인 경우")
        @Nested
        class BlackjackPlayer {
            BettingPlayer blackjackPlayer =
                    new BettingPlayer(CardsFixture.BLACKJACK, DEFAULT_BET);

            @DisplayName("딜러도 블랙잭이라면 상금은 0원이다.")
            @Test
            void blackjackDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BLACKJACK);
                Money prize = blackjackPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(Money.ZERO);
            }

            @DisplayName("딜러가 버스트라면 상금은 1.5배이다.")
            @Test
            void bustedDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BUSTED);
                Money prize = blackjackPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.multiply(1.5));
            }

            @DisplayName("딜러가 일반 카드라면 상금은 1.5배이다.")
            @Test
            void nonBlackjackDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_21);
                Money prize = blackjackPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.multiply(1.5));
            }
        }

        @DisplayName("플레이어가 버스트인 경우")
        @Nested
        class BustPlayer {
            BettingPlayer bustedPlayer =
                    new BettingPlayer(CardsFixture.BUSTED, DEFAULT_BET);

            @DisplayName("딜러가 블랙잭이라면 베팅한 만큼 잃는다.")
            @Test
            void blackjackDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BLACKJACK);
                Money prize = bustedPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.negate());
            }

            @DisplayName("딜러가 버스트라면 베팅한만큼 잃는다.")
            @Test
            void bustedDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BUSTED);
                Money prize = bustedPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.negate());
            }

            @DisplayName("딜러가 일반 카드라면 베팅한만큼 잃는다.")
            @Test
            void normalDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_21);
                Money prize = bustedPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.negate());
            }
        }

        @DisplayName("플레이어가 일반 카드인 경우")
        @Nested
        class NormalPlayer {
            BettingPlayer normalPlayer =
                    new BettingPlayer(CardsFixture.CARDS_SCORE_17, DEFAULT_BET);

            @DisplayName("딜러가 블랙잭이라면 베팅한 만큼 잃는다.")
            @Test
            void blackjackDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BLACKJACK);
                Money prize = normalPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.negate());
            }

            @DisplayName("딜러가 버스트라면 베팅한 만큼 얻는다.")
            @Test
            void bustedDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.BUSTED);
                Money prize = normalPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET);
            }

            @DisplayName("딜러가 일반 카드면서 점수가 플레이어보다 높다면 베팅한 만큼 잃는다.")
            @Test
            void higherDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_18);
                Money prize = normalPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET.negate());
            }

            @DisplayName("딜러가 일반 카드면서 점수가 플레이어와 같다면 상금은 0원이다.")
            @Test
            void drawDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);
                Money prize = normalPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(Money.ZERO);
            }

            @DisplayName("딜러가 일반 카드면서 점수가 플레이어보다 낮다면 베팅한 만큼 얻는다.")
            @Test
            void lowerDealerTest() {
                Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);
                Money prize = normalPlayer.calculatePrize(dealer);

                assertThat(prize).isEqualTo(DEFAULT_BET);
            }
        }
    }
}
