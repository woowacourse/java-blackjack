package blackjack.model.state;

import blackjack.model.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerDrawStateTest {

    @Test
    @DisplayName("딜러가 draw상태에서 stand 상태 도달 - 성공: 카드 점수가 16초과이면 stand 상태로 변화")
    void change_to_stand_if_score_over_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.FIVE);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);

        List<Card> cards = List.of(card1, card2);
        DealerDrawState dealerDrawState = new DealerDrawState(new HandCard(cards));
        Card card3 = Card.of(CardSuit.CLUB, CardNumber.THREE);
        CardDeck cardDeck = new CardDeck(List.of(card3));

        // then
        assertThat(dealerDrawState.draw(cardDeck)).isInstanceOf(StandState.class);
    }
}
