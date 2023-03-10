package domain.player;

import domain.card.BlackJackScore;
import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.TEN;
import static domain.card.CardValue.TWO;
import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.gamblerWithMoneyAndCardArea;
import static domain.fixture.GamblerFixture.코다;
import static domain.player.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Gambler 은")
class GamblerTest {

    @Test
    void 참가자는_상태를_바꿀_수_있다() {
        // given
        final Gambler 코다 = 코다(equal16CardArea());

        // when
        assertDoesNotThrow(() -> 코다.changeState(HitState.HIT));
    }

    @ParameterizedTest(name = "참가자는 버스트되지 않았으면서, STAY 원하지 않을 때(ex: {0}) 카드를 더 받을 수 있다.")
    @EnumSource(mode = EXCLUDE, names = {"STAY"})
    void 참가자는_버스트되지_않았으면서_STAY_를_원하지_않을_때_카드를_더_받을_수_있다(final HitState hitState) {
        // given
        final Gambler 코다 = 코다(equal16CardArea());
        코다.changeState(hitState);

        // when & then
        assertTrue(코다.canHit());
    }

    @ParameterizedTest(name = "참가자는 버스트되었거나, STAY 를 원한다면 카드를 더 받을 수 없다")
    @MethodSource("canNotMoreCard")
    void 참가자는_버스트되었거나_STAY_를_원한다면_카드를_더_받을_수_없다(final CardArea cardArea, final HitState hitState) {
        // given
        final Gambler 코다 = 코다(cardArea);
        코다.changeState(hitState);

        // when & then
        assertFalse(코다.canHit());
    }

    static Stream<Arguments> canNotMoreCard() {
        return Stream.of(
                Arguments.of(under21CardArea(), HitState.STAY),
                Arguments.of(over21CardArea(), HitState.INIT),
                Arguments.of(over21CardArea(), HitState.STAY),
                Arguments.of(over21CardArea(), HitState.HIT)
        );
    }

    @Nested
    @DisplayName("hitOrStay() 테스트")
    class HitOrStayForGamblerTest {
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

        @Test
        void 받을_수_있으며_Hit_을_원한다면_카드를_제공한다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            final BlackJackScore before = 코다.score();
            코다.changeState(HitState.HIT);

            // when
            코다.hitOrStay(cardDeck);

            // then
            assertThat(코다.score()).isNotEqualTo(before);
        }

        @Test
        void Hit_을_원하지_않는다면_카드를_제공하지_않는다() {
            // given
            final Gambler 코다 = 코다(under21CardArea());
            final BlackJackScore before = 코다.score();
            코다.changeState(HitState.STAY);

            // when
            코다.hitOrStay(cardDeck);

            // then
            assertThat(코다.score()).isEqualTo(before);
        }

        @Test
        void hit_을_할_수_없으면_카드를_제공하지_않는다() {
            // given
            final Gambler 코다 = 코다(over21CardArea());
            final BlackJackScore before = 코다.score();
            코다.changeState(HitState.HIT);

            // when
            코다.hitOrStay(cardDeck);

            // then
            assertThat(코다.score()).isEqualTo(before);
        }
    }

    @ParameterizedTest(name = "참가자의 점수가 {0}, 딜러의 점수가 {1} 인 경우, 참가자는 {2}이다")
    @MethodSource("playerAndDealerAndResult")
    void compete_시_대상_참가자와_승부하여_결과를_반환한다(final Gambler gambler, final Dealer dealer, final Revenue actualRevenue) {
        // when
        final Revenue revenue = gambler.compete(dealer);

        // then
        assertThat(revenue).isEqualTo(actualRevenue);
    }

    static Stream<Arguments> playerAndDealerAndResult() {
        // given
        final CardArea cardArea = withTwoCard(TEN, TEN);
        cardArea.addCard(new Card(DIAMOND, TWO));
        final BettingMoney money = BettingMoney.of(1000);
        return Stream.of(
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("17", new Dealer(equal17CardArea())),
                        Named.of("패배", money.revenue(LOSE))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("22", new Dealer(equal22CardArea())),
                        Named.of("승리", money.revenue(WIN))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("21", new Dealer(equal21CardAreaNonBlackJack())),
                        Named.of("패배", money.revenue(LOSE))
                ),
                Arguments.of(
                        Named.of("22", gamblerWithMoneyAndCardArea(money, equal22CardArea())),
                        Named.of("22", new Dealer(equal22CardArea())),
                        Named.of("패배", money.revenue(LOSE))
                ),
                Arguments.of(
                        Named.of("22", gamblerWithMoneyAndCardArea(money, equal22CardArea())),
                        Named.of("19", new Dealer(equal19CardArea())),
                        Named.of("패배", money.revenue(LOSE))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaNonBlackJack())),
                        Named.of("21", new Dealer(equal21CardAreaNonBlackJack())),
                        Named.of("무승부", money.revenue(DRAW))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaNonBlackJack())),
                        Named.of("20", new Dealer(equal20CardArea())),
                        Named.of("승리", money.revenue(WIN))
                ),
                Arguments.of(
                        Named.of("16", gamblerWithMoneyAndCardArea(money, equal16CardArea())),
                        Named.of("16", new Dealer(equal16CardArea())),
                        Named.of("무승부", money.revenue(DRAW))
                ),
                Arguments.of(
                        Named.of("21", gamblerWithMoneyAndCardArea(money, equal21CardAreaBlackJack())),
                        Named.of("20", new Dealer(equal20CardArea())),
                        Named.of("블랙잭 승리", money.revenue(BLACKJACK_WIN))
                )
        );
    }
}
