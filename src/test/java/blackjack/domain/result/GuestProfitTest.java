package blackjack.domain.result;

import static blackjack.domain.Fixture.ACE_CLOVER;
import static blackjack.domain.Fixture.ACE_HEART;
import static blackjack.domain.Fixture.FIVE_CLOVER;
import static blackjack.domain.Fixture.FIVE_HEART;
import static blackjack.domain.Fixture.KING_HEART;
import static blackjack.domain.Fixture.NINE_HEART;
import static blackjack.domain.Fixture.takeTwoCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.money.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GuestProfitTest {

    private Dealer dealer;
    private Guest jennie;

    @Nested
    @DisplayName("참가자의 수익을 계산한다.")
    class CalculateTest {

        @BeforeEach
        void setUp() {
            dealer = new Dealer();
            takeTwoCards(dealer, ACE_CLOVER, FIVE_CLOVER);
            jennie = new Guest("제니");
            jennie.betMoney(Money.valueOf(10_000));
        }

        @Test
        @DisplayName("BLACKJACK인 경우 수익은 1.5배한 금액이다.")
        void blackjack() {
            takeTwoCards(jennie, ACE_HEART, KING_HEART);

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(jennie)));
            Map<Guest, Money> profits = guestProfit.getProfits();

            assertThat(profits).contains(entry(jennie, Money.valueOf(15_000)));
        }

        @Test
        @DisplayName("WIN인 경우 수익은 1배한 금액이다.")
        void win() {
            takeTwoCards(jennie, ACE_HEART, NINE_HEART);

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(jennie)));
            Map<Guest, Money> profits = guestProfit.getProfits();

            assertThat(profits).contains(entry(jennie, Money.valueOf(10_000)));
        }

        @Test
        @DisplayName("DRAW인 경우 수익은 0배한 금액이다.")
        void draw() {
            takeTwoCards(jennie, ACE_HEART, FIVE_HEART);

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(jennie)));
            Map<Guest, Money> profits = guestProfit.getProfits();

            assertThat(profits).contains(entry(jennie, Money.valueOf(0)));
        }

        @Test
        @DisplayName("LOSE인 경우 수익은 -1배한 금액이다.")
        void lose() {
            takeTwoCards(jennie, KING_HEART, FIVE_HEART);

            GuestProfit guestProfit = new GuestProfit(dealer, Guests.of(List.of(jennie)));
            Map<Guest, Money> profits = guestProfit.getProfits();

            assertThat(profits).contains(entry(jennie, Money.valueOf(-10_000)));

        }
    }
}
