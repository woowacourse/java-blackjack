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
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GuestProfitTest {

    private Dealer dealer;
    private Guest lisa;
    private Guest rose;

    @Nested
    @DisplayName("참가자의 수익을 계산한다.")
    class CalculateTest {

        @BeforeEach
        void setUp() {
            dealer = new Dealer();
            takeTwoCards(dealer, ACE_CLOVER, FIVE_CLOVER);

            lisa = new Guest("리사");
            lisa.betMoney(new Money(10_000));
            rose = new Guest("채영");
            rose.betMoney(new Money(10_000));
        }

        @Test
        @DisplayName("BLACKJACK인 경우 수익은 1.5배한 금액이다.")
        void blackjack() {
            takeTwoCards(lisa, ACE_HEART, KING_HEART);
            takeTwoCards(rose, ACE_HEART, KING_HEART);

            GuestProfits guestProfits = new GuestProfits(dealer, Guests.of(lisa, rose));
            Map<Guest, Money> profits = guestProfits.getProfits();

            assertThat(profits).contains(entry(lisa, new Money(15_000)),
                    entry(rose, new Money(15_000)));
        }

        @Test
        @DisplayName("WIN인 경우 수익은 1배한 금액이다.")
        void win() {
            takeTwoCards(lisa, ACE_HEART, NINE_HEART);
            takeTwoCards(rose, ACE_HEART, NINE_HEART);

            GuestProfits guestProfits = new GuestProfits(dealer, Guests.of(lisa, rose));
            Map<Guest, Money> profits = guestProfits.getProfits();

            assertThat(profits).contains(entry(lisa, new Money(10_000)),
                    entry(rose, new Money(10_000)));
        }

        @Test
        @DisplayName("DRAW인 경우 수익은 0배한 금액이다.")
        void draw() {
            takeTwoCards(lisa, ACE_HEART, FIVE_HEART);
            takeTwoCards(rose, ACE_HEART, FIVE_HEART);

            GuestProfits guestProfits = new GuestProfits(dealer, Guests.of(lisa, rose));
            Map<Guest, Money> profits = guestProfits.getProfits();

            assertThat(profits).contains(entry(lisa, new Money(0)),
                    entry(rose, new Money(0)));
        }

        @Test
        @DisplayName("LOSE인 경우 수익은 -1배한 금액이다.")
        void lose() {
            takeTwoCards(lisa, KING_HEART, FIVE_HEART);
            takeTwoCards(rose, KING_HEART, FIVE_HEART);

            GuestProfits guestProfits = new GuestProfits(dealer, Guests.of(lisa, rose));
            Map<Guest, Money> profits = guestProfits.getProfits();

            assertThat(profits).contains(entry(lisa, new Money(-10_000)),
                    entry(rose, new Money(-10_000)));

        }
    }
}
