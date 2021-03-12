package blackjack.domain.player.dealercardstate;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllCardsOpenStateDealerTest {
    @DisplayName("자기자신을 반환하는지 테스트")
    @Test
    void AllCardOpen() {
        DealerCardOpenState dealerCardOpenState = new AllCardsOpenStateDealer();
        assertThat(dealerCardOpenState.stateChange()).isInstanceOf(AllCardsOpenStateDealer.class);

        assertThat(dealerCardOpenState.stateChange()).isSameAs(dealerCardOpenState);
    }

    @DisplayName("모든 카드를 반환하는지 테스트")
    @Test
    void cards() {
        Hand hand = new Hand();
        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.TEN));

        DealerCardOpenState dealerCardOpenState = new AllCardsOpenStateDealer();
        assertThat(dealerCardOpenState.cards(hand.getCards())).hasSize(2);

        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.FIVE));
        assertThat(dealerCardOpenState.cards(hand.getCards())).hasSize(3);
    }
}