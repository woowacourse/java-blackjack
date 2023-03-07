package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardShape;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;
import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.말랑;
import static domain.player.DealerCompeteResult.*;
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

        return Stream.of(
                Arguments.of(Named.of("16", 말랑(equal16CardArea())), Named.of("17", new Dealer(equal17CardArea())), WIN),
                Arguments.of(Named.of("16", 말랑(equal16CardArea())), Named.of("22", new Dealer(equal22CardArea())), LOSE),
                Arguments.of(Named.of("16", 말랑(equal16CardArea())), Named.of("21", new Dealer(equal21CardArea())), WIN),
                Arguments.of(Named.of("22", 말랑(equal22CardArea())), Named.of("22", new Dealer(equal22CardArea())), WIN),
                Arguments.of(Named.of("22", 말랑(equal22CardArea())), Named.of("19", new Dealer(equal19CardArea())), WIN),
                Arguments.of(Named.of("21", 말랑(equal21CardArea())), Named.of("21", new Dealer(equal21CardArea())), DRAW),
                Arguments.of(Named.of("21", 말랑(equal21CardArea())), Named.of("20", new Dealer(equal20CardArea())), LOSE),
                Arguments.of(Named.of("16", 말랑(equal16CardArea())), Named.of("16", new Dealer(equal16CardArea())), DRAW)
        );
    }

    @ParameterizedTest(name = "참가자의 점수가 {0}, 딜러의 점수가 {1} 인 경우, 딜러는 {2} 이다")
    @MethodSource("playerAndDealerAndResult")
    void compete_시_대상_참가자와_승부하여_결과를_반환한다(final Participant participant, final Dealer dealer, final DealerCompeteResult dealerCompeteResult) {
        // when
        final DealerCompeteResult judge = dealer.compete(participant);

        // then
        assertThat(judge).isEqualTo(dealerCompeteResult);
    }
}