package blackjack.domain.result;

import static blackjack.domain.Fixture.ACE_CLOVER;
import static blackjack.domain.Fixture.ACE_HEART;
import static blackjack.domain.Fixture.FIVE_CLOVER;
import static blackjack.domain.Fixture.FIVE_HEART;
import static blackjack.domain.Fixture.KING_CLOVER;
import static blackjack.domain.Fixture.KING_HEART;
import static blackjack.domain.Fixture.NINE_CLOVER;
import static blackjack.domain.Fixture.TEN_CLOVER;
import static blackjack.domain.Fixture.TEN_HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class ResultTest {

    @Nested
    @DisplayName("승패에 따라 참가자의 수익률을 반환한다.")
    class DecideTest {

        @Nested
        @DisplayName("딜러가 블랙잭인 경우")
        class DealerIsNotBustTest {

            @Test
            @DisplayName("참가자가 블랙잭이면 0")
            void blackjack() {
                runTest(ACE_CLOVER, KING_CLOVER, ACE_HEART, KING_HEART, 0);
            }

            @Test
            @DisplayName("참가자의 점수가 더 낮으면 -1")
            void lessThan() {
                runTest(ACE_CLOVER, KING_CLOVER, ACE_HEART, FIVE_HEART, -1);
            }
        }

        @Nested
        @DisplayName("딜러의 점수가 21미만인 경우")
        class DealerLessThan21Test {

            @Test
            @DisplayName("참가자가 블랙잭이면 1.5")
            void blackjack() {
                runTest(NINE_CLOVER, KING_CLOVER, ACE_HEART, KING_HEART, 1.5);
            }

            @Test
            @DisplayName("참가자의 점수가 더 높으면 1")
            void biggerThan() {
                runTest(NINE_CLOVER, FIVE_CLOVER, FIVE_HEART, KING_HEART, 1);
            }

            @Test
            @DisplayName("참가자의 점수가 21이면 1")
            void is21() {
                runGuestMoreCardTest(NINE_CLOVER, FIVE_CLOVER, ACE_HEART, KING_HEART, TEN_HEART, 1);
            }

            @Test
            @DisplayName("참가자의 점수가 같으면 0")
            void same() {
                runTest(FIVE_CLOVER, KING_CLOVER, FIVE_HEART, KING_HEART, 0);
            }

            @Test
            @DisplayName("참가자의 점수가 더 낮으면 -1")
            void lessThan() {
                runTest(NINE_CLOVER, KING_CLOVER, ACE_HEART, FIVE_HEART, -1);
            }

            @Test
            @DisplayName("참가자의 점수가 21보다 크면 -1")
            void bust() {
                runGuestMoreCardTest(FIVE_CLOVER, KING_CLOVER, TEN_HEART, FIVE_HEART, KING_HEART, -1);
            }
        }

        @Nested
        @DisplayName("딜러의 점수가 21초과인 경우")
        class DealerBiggerThan21Test {

            @Test
            @DisplayName("참가자의 점수가 21보다 크면 -1")
            void bust() {
                runDealerAndGuestMoreCardTest(FIVE_CLOVER, KING_CLOVER, TEN_CLOVER,
                        FIVE_HEART, KING_HEART, TEN_HEART, -1);
            }

            @Test
            @DisplayName("참가자의 점수가 21이하면 1")
            void equalOrLessThan21() {
                runDealerAndGuestMoreCardTest(FIVE_CLOVER, KING_CLOVER, TEN_CLOVER,
                        ACE_HEART, KING_HEART, FIVE_HEART, 1);
            }
        }

        private void runTest(Card card1, Card card2, Card card3, Card card4, double expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(card1);
            dealer.takeCard(card2);

            Guest guest = new Guest("엘리");
            guest.takeCard(card3);
            guest.takeCard(card4);

            assertThat(Result.decide(dealer, guest).getProfitRatio()).isEqualTo(expected);
        }

        private void runGuestMoreCardTest(Card card1, Card card2, Card card3, Card card4,
                                          Card card5, double expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(card1);
            dealer.takeCard(card2);

            Guest guest = new Guest("엘리");
            guest.takeCard(card3);
            guest.takeCard(card4);
            guest.takeCard(card5);

            assertThat(Result.decide(dealer, guest).getProfitRatio()).isEqualTo(expected);
        }

        private void runDealerAndGuestMoreCardTest(Card card1, Card card2, Card card3,
                                                   Card card4, Card card5, Card card6,
                                                   double expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(card1);
            dealer.takeCard(card2);
            dealer.takeCard(card3);

            Guest guest = new Guest("엘리");
            guest.takeCard(card4);
            guest.takeCard(card5);
            guest.takeCard(card6);

            assertThat(Result.decide(dealer, guest).getProfitRatio()).isEqualTo(expected);
        }
    }
}
