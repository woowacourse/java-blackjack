package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardShape;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;
import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.gamblerWithMoneyAndCardArea;
import static domain.player.Revenue.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Dealer 은")
class DealerTest {

    @Test
    void 딜러는_16이하면_카드를_더_받을_수_있다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SIX)
        );

        final Participant dealer = new Dealer(cardArea);

        // when & then
        assertTrue(dealer.canHit());
    }

    @Test
    void 딜러는_16초과면_카드를_더_받을_수_없다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        final Participant dealer = new Dealer(cardArea);

        // when & then
        assertFalse(dealer.canHit());
    }

    @Test
    void 딜러는_첫_장만_보여줄_수_있다() {
        // given
        final CardArea cardArea = new CardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        final Dealer dealer = new Dealer(cardArea);

        // then
        assertEquals(dealer.firstCard(), new Card(CardShape.CLOVER, TEN));
    }

    @Test
    void 딜러의_이름은_항상_딜러이다() {
        // given
        final Dealer dealer1 = new Dealer(equal16CardArea());
        final Dealer dealer2 = new Dealer(equal16CardArea());

        // when
        assertAll(
                () -> assertThat(dealer1.name()).isEqualTo(dealer2.name()),
                () -> assertThat(dealer1.nameValue()).isEqualTo("딜러")
        );
    }

    static Stream<Arguments> playerAndDealerAndResult() {
        final CardArea cardArea22 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
        cardArea22.addCard(new Card(DIAMOND, TWO));
        final BettingMoney money = BettingMoney.of(1000);
        return Stream.of(
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("17", new Dealer(equal17CardArea())),
                        Named.of("패배", lose(money))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("22", new Dealer(equal22CardArea())),
                        Named.of("승리", defaultWin(money))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("21", new Dealer(equal21CardAreaNonBlackJack())),
                        Named.of("패배", lose(money))
                ),
                Arguments.of(
                        Named.of("22", gamblerWithMoneyAndCardArea(money, equal22CardArea())),
                        Named.of("22", new Dealer(equal22CardArea())),
                        Named.of("패배", lose(money))
                ),
                Arguments.of(
                        Named.of("22", gamblerWithMoneyAndCardArea(money, equal22CardArea())),
                        Named.of("19", new Dealer(equal19CardArea())),
                        Named.of("패배", lose(money))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaNonBlackJack())),
                        Named.of("21", new Dealer(equal21CardAreaNonBlackJack())),
                        Named.of("무승부", draw(money))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaNonBlackJack())),
                        Named.of("20", new Dealer(equal20CardArea())),
                        Named.of("승리", defaultWin(money))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("16", new Dealer(equal16CardArea())),
                        Named.of("무승부", draw(money))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaBlackJack())),
                        Named.of("20", new Dealer(equal20CardArea())),
                        Named.of("블랙잭 승리", blackJackWin(money))
                )
        );
    }

    @ParameterizedTest(name = "참가자의 점수가 {0}, 딜러의 점수가 {1} 인 경우, 참가자는 {2}이다")
    @MethodSource("playerAndDealerAndResult")
    void compete_시_대상_참가자와_승부하여_결과를_반환한다(final Gambler gambler, final Dealer dealer, final Revenue actualRevenue) {
        // when
        final Revenue revenue = dealer.compete(gambler);

        // then
        assertThat(revenue).isEqualTo(actualRevenue);
    }

    @SuppressWarnings("NonAsciiCharacters")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("Revenue 은")
    public static class RevenueTest {

        @ParameterizedTest(name = "{0} 원을 배팅한 후 진다면 수익은 {1} 원이 된다.")
        @CsvSource({
                "1000, -1000",
                "50000, -50000",
        })
        void lose_시_수익은_기존_배팅_금액만큼_차감된_값이다(final int battingAmount, final int revenueAmount) {
            // given
            final BettingMoney money = BettingMoney.of(battingAmount);

            // when
            final Revenue lose = Revenue.lose(money);

            // then
            assertThat(lose.amount()).isEqualTo(revenueAmount);
        }

        @Test
        void draw_시_수익은_0원이다() {
            // when
            final Revenue draw = Revenue.draw(BettingMoney.of(10000));

            // then
            assertThat(draw.amount()).isEqualTo(0);
        }

        @ParameterizedTest(name = "이긴 경우 블랙잭으로 이긴 것이 아니라면, 자신이 배팅한 금액만큼을 수익으로 얻는다. 예를 들어 {0}원을 걸고 이겼다면 수익은 {1}원이다")
        @CsvSource({
                "1000, 1000",
                "5000, 5000",
                "10000, 10000",
        })
        void 이긴_경우_블랙잭이_아니라면_수익은_배팅한_금액과_같다(final int battingAmount, final int revenueAmount) {
            // given
            final BettingMoney money = BettingMoney.of(battingAmount);

            // when
            final Revenue defaultWin = defaultWin(money);

            // then
            assertThat(defaultWin.amount()).isEqualTo(revenueAmount);
        }

        @ParameterizedTest(name = "이긴 경우 블랙잭이라면, 자신이 배팅한 금액의 1.5배를 수익으로 얻는다. 예를 들어 {0}원을 걸고 블랙잭으로 이겼다면 수익은 {1}원이다")
        @CsvSource({
                "1000, 1500",
                "10000, 15000",
        })
        void 이긴_경우_블랙잭이라면_수익은_배팅한_금액의_일점오배와_같다(final int battingAmount, final int revenueAmount) {
            // given
            final BettingMoney money = BettingMoney.of(battingAmount);

            // when
            final Revenue blackJackWin = Revenue.blackJackWin(money);

            // then
            assertThat(blackJackWin.amount()).isEqualTo(revenueAmount);
        }

        @Test
        void Revenue_끼리는_뺄_수_있다() {
            // given
            final Revenue revenue1 = revenue(1000);
            final Revenue revenue2 = revenue(3000);

            // when
            final Revenue result = revenue1.minus(revenue2);

            // then
            assertThat(result.amount()).isEqualTo(-2000);
        }

        private Revenue revenue(final int amount) {
            return defaultWin(BettingMoney.of(amount));
        }
    }
}
