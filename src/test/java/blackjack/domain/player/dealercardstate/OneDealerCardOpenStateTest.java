package blackjack.domain.player.dealercardstate;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OneDealerCardOpenStateTest {
    @DisplayName("oneCardOpen 에서 상태변경시 allOpen 으로 바뀌는지 테스트")
    @Test
    void allOpen() {
        DealerCardOpenState dealerCardOpenState = new OneDealerCardOpenState();
        assertThat(dealerCardOpenState.stateChange()).isInstanceOf(AllCardsOpenStateDealer.class);
    }

    @DisplayName("카드를 무조건 1장 반환 테스트")
    @Test
    void cards() {
        Hand hand = new Hand();
        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.TEN));

        DealerCardOpenState dealerCardOpenState = new OneDealerCardOpenState();
        assertThat(dealerCardOpenState.cards(hand.getCards())).hasSize(1);

        hand.add(Card.valueOf(CardShape.DIAMOND, CardNumber.FIVE));
        assertThat(dealerCardOpenState.cards(hand.getCards())).hasSize(1);
    }
}