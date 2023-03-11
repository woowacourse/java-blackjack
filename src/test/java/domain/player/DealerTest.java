package domain.player;

import domain.card.CardArea;
import domain.card.CardDeck;
import domain.fixture.CardAreaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.JACK;
import static domain.card.CardValue.TEN;
import static domain.fixture.CardAreaFixture.*;
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
        final CardArea cardArea = withTwoCard(TEN, JACK);
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

    @Nested
    @DisplayName("hitOrStay() 테스트")
    class HitOrStayForGamblerTest {
        CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

        @Test
        void 딜러의_카드가_16_점_이하이면_항상_Hit_을_더_해야한다() {
            // given
            final Dealer dealer = new Dealer(equal16CardArea());

            // when & then
            assertThat(dealer.hitOrStay(cardDeck)).isTrue();
        }

        @Test
        void 딜러의_카드가_16_점_초과이면_항상_Hit_을_더_하지_않는다() {
            // given
            final Dealer dealer = new Dealer(over16CardArea());

            // when & then
            assertThat(dealer.hitOrStay(cardDeck)).isFalse();
        }
    }
}
