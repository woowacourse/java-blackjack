package blackjack.domain.result;

import static blackjack.domain.Fixture.ACE_CLOVER;
import static blackjack.domain.Fixture.ACE_HEART;
import static blackjack.domain.Fixture.FIVE_CLOVER;
import static blackjack.domain.Fixture.FIVE_HEART;
import static blackjack.domain.Fixture.KING_CLOVER;
import static blackjack.domain.Fixture.KING_HEART;
import static blackjack.domain.Fixture.NINE_HEART;
import static blackjack.domain.Fixture.TEN_CLOVER;
import static blackjack.domain.Fixture.TEN_HEART;
import static blackjack.domain.Fixture.takeThreeCards;
import static blackjack.domain.Fixture.takeTwoCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class ResultTest {

    private Dealer dealer;
    private Guest ellie;

    @Nested
    @DisplayName("승패에 따라 참가자의 수익률을 반환한다.")
    class DecideTest {

        @Nested
        @DisplayName("딜러가 블랙잭인 경우")
        class DealerIsNotBustTest {

            @BeforeEach
            void setUp() {
                dealer = new Dealer();
                takeTwoCards(dealer, ACE_CLOVER, KING_CLOVER);
                ellie = new Guest("엘리");
            }

            @Test
            @DisplayName("참가자가 블랙잭이면 0")
            void blackjack() {
                takeTwoCards(ellie, ACE_HEART, KING_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(0);
            }

            @Test
            @DisplayName("참가자의 점수가 더 낮으면 -1")
            void lessThan() {
                takeTwoCards(ellie, ACE_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(-1);
            }
        }

        @Nested
        @DisplayName("딜러의 점수가 21미만인 경우")
        class DealerLessThan21Test {

            @BeforeEach
            void setUp() {
                dealer = new Dealer();
                takeTwoCards(dealer, KING_CLOVER, FIVE_CLOVER);
                ellie = new Guest("엘리");
            }

            @Test
            @DisplayName("참가자가 블랙잭이면 1.5")
            void blackjack() {
                takeTwoCards(ellie, ACE_HEART, KING_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(1.5);
            }

            @Test
            @DisplayName("참가자의 점수가 더 높으면 1")
            void biggerThan() {
                takeTwoCards(ellie, KING_HEART, NINE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(1);
            }

            @Test
            @DisplayName("참가자의 점수가 21이면 1")
            void is21() {
                takeThreeCards(ellie, ACE_HEART, TEN_HEART, KING_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(1);
            }

            @Test
            @DisplayName("참가자의 점수가 같으면 0")
            void same() {
                takeTwoCards(ellie, KING_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(0);
            }

            @Test
            @DisplayName("참가자의 점수가 더 낮으면 -1")
            void lessThan() {
                takeTwoCards(ellie, NINE_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(-1);
            }

            @Test
            @DisplayName("참가자의 점수가 21보다 크면 -1")
            void bust() {
                takeThreeCards(ellie, TEN_HEART, KING_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(-1);
            }
        }

        @Nested
        @DisplayName("딜러의 점수가 21초과인 경우")
        class DealerBiggerThan21Test {

            @BeforeEach
            void setUp() {
                dealer = new Dealer();
                takeThreeCards(dealer, KING_CLOVER, TEN_CLOVER, FIVE_CLOVER);
                ellie = new Guest("엘리");
            }

            @Test
            @DisplayName("참가자의 점수가 21보다 크면 -1")
            void bust() {
                takeThreeCards(ellie, KING_HEART, TEN_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(-1);
            }

            @Test
            @DisplayName("참가자의 점수가 21이하면 1")
            void equalOrLessThan21() {
                takeThreeCards(ellie, ACE_HEART, KING_HEART, FIVE_HEART);

                assertThat(Result.decide(dealer, ellie).getProfitRatio()).isEqualTo(1);
            }
        }
    }
}
