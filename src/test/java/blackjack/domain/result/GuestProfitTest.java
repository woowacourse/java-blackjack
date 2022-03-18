package blackjack.domain.result;

import static blackjack.domain.Fixture.ACE_CLOVER;
import static blackjack.domain.Fixture.ACE_HEART;
import static blackjack.domain.Fixture.FIVE_CLOVER;
import static blackjack.domain.Fixture.FIVE_HEART;
import static blackjack.domain.Fixture.KING_CLOVER;
import static blackjack.domain.Fixture.NINE_CLOVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GuestProfitTest {

    @Nested
    @DisplayName("참가자의 수익을 계산한다.")
    class CalculateTest {

        @Test
        @DisplayName("블랙잭인 경우 수익은 1.5배한 금액이다.")
        void blackjack() {
            runTest(ACE_CLOVER, FIVE_CLOVER, ACE_HEART, KING_CLOVER, 15_000);
        }

        @Test
        @DisplayName("일반 승리인 경우 수익은 1배한 금액이다.")
        void win() {
            runTest(ACE_CLOVER, FIVE_CLOVER, NINE_CLOVER, ACE_HEART, 10_000);
        }

        @Test
        @DisplayName("무승부인 경우 수익은 0배한 금액이다.")
        void draw() {
            runTest(ACE_CLOVER, FIVE_CLOVER, ACE_HEART, FIVE_HEART, 0);
        }

        @Test
        @DisplayName("패한 경우 수익은 -1배한 금액이다.")
        void lose() {
            runTest(ACE_CLOVER, FIVE_CLOVER, KING_CLOVER, FIVE_HEART, -10_000);
        }

        private void runTest(Card card1, Card card2, Card card3, Card card4, long expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(card1);
            dealer.takeCard(card2);

            Guests guests = new Guests(List.of("김제니", "박채영"));
            for (Guest guest : guests) {
                guest.takeCard(card3);
                guest.takeCard(card4);
                guest.betMoney(Money.valueOf(10_000));
            }

            GuestProfit guestProfit = new GuestProfit(dealer, guests);
            Map<Guest, Money> profits = guestProfit.getProfits();

            for (Guest guest : guests) {
                assertThat(profits).contains(entry(guest, Money.valueOf(expected)));
            }
        }
    }
}
