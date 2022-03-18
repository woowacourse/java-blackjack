package blackjack.domain.result;

import static blackjack.domain.Fixture.ACE_CLOVER;
import static blackjack.domain.Fixture.ACE_HEART;
import static blackjack.domain.Fixture.FIVE_CLOVER;
import static blackjack.domain.Fixture.FIVE_HEART;
import static blackjack.domain.Fixture.KING_CLOVER;
import static blackjack.domain.Fixture.NINE_CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerProfitTest {

    @Nested
    @DisplayName("딜러의 수익을 계산한다.")
    class CalculateTest {

        @Test
        @DisplayName("참가자가 블랙잭 승리인 경우 수익은 -1.5배한 금액이다.")
        void blackjack() {
            runTest(ACE_CLOVER, FIVE_CLOVER, ACE_HEART, KING_CLOVER, -15_000);
        }

        @Test
        @DisplayName("참가자가 일반 승리인 경우 수익은 -1배한 금액이다.")
        void win() {
            runTest(ACE_CLOVER, FIVE_CLOVER, NINE_CLOVER, ACE_HEART, -10_000);
        }

        @Test
        @DisplayName("참가자가 무승부인 경우 수익은 0배한 금액이다.")
        void draw() {
            runTest(ACE_CLOVER, FIVE_CLOVER, ACE_HEART, FIVE_HEART, 0);
        }

        @Test
        @DisplayName("참가자가 패한 경우 수익은 1배한 금액이다.")
        void lose() {
            runTest(ACE_CLOVER, FIVE_CLOVER, KING_CLOVER, FIVE_HEART, 10_000);
        }

        private void runTest(Card card1, Card card2, Card card3, Card card4, long expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(card1);
            dealer.takeCard(card2);

            Guest guest = new Guest("리사");
            guest.takeCard(card3);
            guest.takeCard(card4);
            guest.betMoney(Money.valueOf(10_000));

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(guest)));
            DealerProfit dealerProfit = new DealerProfit(guestProfit);

            assertThat(dealerProfit.getProfit()).isEqualTo(Money.valueOf(expected));
        }
    }
}
