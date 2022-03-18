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
        @DisplayName("BLACKJACK인 경우 수익은 1.5배한 금액이다.")
        void blackjack() {
            runTest(ACE_HEART, KING_CLOVER, 15_000);
        }

        @Test
        @DisplayName("WIN인 경우 수익은 1배한 금액이다.")
        void win() {
            runTest(NINE_CLOVER, ACE_HEART, 10_000);
        }

        @Test
        @DisplayName("DRAW인 경우 수익은 0배한 금액이다.")
        void draw() {
            runTest(ACE_HEART, FIVE_HEART, 0);
        }

        @Test
        @DisplayName("LOSE인 경우 수익은 -1배한 금액이다.")
        void lose() {
            runTest(KING_CLOVER, FIVE_HEART, -10_000);
        }

        private void runTest(Card card1, Card card2, long expected) {
            Dealer dealer = new Dealer();
            dealer.takeCard(ACE_CLOVER);
            dealer.takeCard(FIVE_CLOVER);

            Guest guest = new Guest("제니");
            guest.takeCard(card1);
            guest.takeCard(card2);
            guest.betMoney(Money.valueOf(10_000));

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(guest)));
            Map<Guest, Money> profits = guestProfit.getProfits();

            assertThat(profits).contains(entry(guest, Money.valueOf(expected)));
        }
    }
}
