package blackjack.domain.user;

import blackjack.domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.클로버_10;
import static blackjack.domain.fixture.FixtureCard.클로버_9;
import static blackjack.domain.fixture.FixtureCard.클로버_A;
import static blackjack.domain.fixture.FixtureCard.하트_10;

class HandTest {

    private static final int MAX_CARD_SCORE = 21;

    @Test
    void 카드를_받아_점수를_계산한다() {
        //given
        List<Card> dummy = List.of(클로버_10);
        Hand hand = new Hand(dummy);

        // then
        Assertions.assertThat(hand.getScore().getValue())
                .isEqualTo(10);
    }

    @Nested
    class 에이스_카드는 {

        @Test
        void _카드_총합이_21이_넘어가면_1로_취급한다() {
            // given
            List<Card> cards = List.of(클로버_10, 클로버_9, 클로버_A);
            Hand hand = new Hand(cards);

            // then
            Assertions.assertThat(hand.getScore().getValue())
                    .isEqualTo(20);
        }

        @Test
        void _카드_총합이_21이_넘어가지_않으면_11로_취급한다() {
            // given
            List<Card> cards = List.of(클로버_10, 클로버_A);
            Hand hand = new Hand(cards);

            // then
            Assertions.assertThat(hand.getScore().getValue())
                    .isEqualTo(MAX_CARD_SCORE);
        }
    }

    @Test
    void _카드_총합이_21을_넘어가면_BUST이다() {
        // given
        List<Card> cards = List.of(클로버_10, 스페이드_10, 하트_10);
        Hand hand = new Hand(cards);

        //then
        Assertions.assertThat(hand.isBust()).isTrue();
    }
}
