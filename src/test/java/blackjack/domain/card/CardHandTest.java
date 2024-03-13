package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardHandTest {

    @Test
    void 카드를_받을_수_있다() {
        final CardHand cardHand = new CardHand();
        cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));

        assertThat(cardHand).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .containsExactly(new Card(Suit.DIAMOND, Denomination.KING));
    }

    @Test
    void 카드_패의_총_점수를_계산할_수_있다() {
        final CardHand cardHand = new CardHand();
        cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.TEN));
        cardHand.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final int result = cardHand.sumAllScore();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void J_Q_K는_전부_10으로_계산한다() {
        final CardHand cardHand = new CardHand();
        cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        cardHand.receiveCard(new Card(Suit.SPADE, Denomination.QUEEN));
        cardHand.receiveCard(new Card(Suit.SPADE, Denomination.KING));

        final int result = cardHand.sumAllScore();

        assertThat(result).isEqualTo(30);
    }

    @Nested
    @DisplayName("Ace는 1 또는 11로 계산할 수 있다.")
    class SumAllScoreWithAce {

        @Test
        void Ace는_11로_계산할_수_있다() {
            final CardHand cardHand = new CardHand();
            cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));
            cardHand.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

            final int result = cardHand.sumAllScore();

            assertThat(result).isEqualTo(17);
        }

        @Test
        void Ace는_1로_계산할_수_있다() {
            final CardHand cardHand = new CardHand();
            cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
            cardHand.receiveCard(new Card(Suit.SPADE, Denomination.TEN));
            cardHand.receiveCard(new Card(Suit.HEART, Denomination.ACE));

            final int result = cardHand.sumAllScore();

            assertThat(result).isEqualTo(21);
        }

        @Test
        void Ace는_1과_11로_계산할_수_있다() {
            final CardHand cardHand = new CardHand();
            cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));
            cardHand.receiveCard(new Card(Suit.HEART, Denomination.ACE));

            final int result = cardHand.sumAllScore();

            assertThat(result).isEqualTo(12);
        }
    }
}
