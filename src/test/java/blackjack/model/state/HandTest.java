package blackjack.model.state;

import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("핸드 테스트")
class HandTest {

    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void addCardTest() {
        // given
        Hand hand = new Hand();

        // when
        Hand newHand = hand.add(createCard(Suit.SPADES, CardValue.ACE));

        // then
        assertThat(newHand.size())
            .isEqualTo(1);
    }

    @DisplayName("카드의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Hand hand = new Hand();

        // when
        Hand newHand = hand.add(SPADE_TEN_CARD)
                .add(SPADE_SIX_CARD);

        // then
        assertThat(newHand.getTotal())
                .isEqualTo(16);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Hand hand = new Hand();

        // when
        Hand newHand = hand.add(SPADE_ACE_CARD)
                .add(SPADE_TEN_CARD);

        // then
        assertThat(newHand.getTotal())
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Hand hand = new Hand();

        // when
        Hand newHand = hand.add(SPADE_ACE_CARD)
                .add(SPADE_TWO_CARD)
                .add(SPADE_TEN_CARD);

        // then
        assertThat(newHand.getTotal())
                .isEqualTo(13);
    }
}
