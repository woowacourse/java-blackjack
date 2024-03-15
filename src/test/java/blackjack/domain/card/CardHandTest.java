package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.fixture.CardFixture.카드;
import static blackjack.fixture.ScoreFixture.점수;
import static org.assertj.core.api.Assertions.assertThat;

class CardHandTest {
    private CardHand cardHand;

    @BeforeEach
    void setUp() {
        cardHand = new CardHand();
    }

    @Test
    void 카드를_받을_수_있다() {
        final Card card = 카드(KING);
        cardHand.receiveCard(card);

        assertThat(cardHand.getCards()).containsExactly(card);
    }

    @Test
    void 카드_패의_총_점수를_계산할_수_있다() {
        cardHand.receiveCard(카드(KING));
        cardHand.receiveCard(카드(SIX));

        final Score result = cardHand.sumAllScore();

        assertThat(result).isEqualTo(점수(16));
    }

    @Test
    void J_Q_K는_전부_10으로_계산한다() {
        cardHand.receiveCard(카드(JACK));
        cardHand.receiveCard(카드(QUEEN));
        cardHand.receiveCard(카드(KING));

        final Score result = cardHand.sumAllScore();

        assertThat(result).isEqualTo(점수(30));
    }

    @Nested
    @DisplayName("Ace는 1 또는 11로 계산할 수 있다.")
    class SumAllScoreWithAce {

        @Test
        void Ace는_11로_계산할_수_있다() {
            cardHand.receiveCard(카드(ACE));
            cardHand.receiveCard(카드(SIX));

            final Score result = cardHand.sumAllScore();

            assertThat(result).isEqualTo(점수(17));
        }

        @Test
        void Ace는_1로_계산할_수_있다() {
            cardHand.receiveCard(카드(KING));
            cardHand.receiveCard(카드(TEN));
            cardHand.receiveCard(카드(ACE));

            final Score result = cardHand.sumAllScore();

            assertThat(result).isEqualTo(점수(21));
        }
    }
}
