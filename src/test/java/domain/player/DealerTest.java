package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import domain.fixture.CardAreaFixture;
import domain.fixture.CardDeckFixture;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
        final Participant dealer = new Dealer(CardAreaFixture.under16CardArea());

        // when & then
        assertTrue(dealer.canHit());
    }

    @Test
    void 딜러는_16초과면_카드를_더_받을_수_없다() {
        // given
        final Participant dealer = new Dealer(CardAreaFixture.over16CardArea());

        // when & then
        assertFalse(dealer.canHit());
    }

    @Test
    void 딜러는_첫_장만_보여줄_수_있다() {
        // given
        final CardArea cardArea = CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, JACK));
        final Dealer dealer = new Dealer(cardArea);

        // then
        assertEquals(dealer.firstCard().cardValue(), TEN);
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
        // given
        final CardArea cardArea = CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, TEN));
        cardArea.addCard(new Card(DIAMOND, TWO));
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
}
