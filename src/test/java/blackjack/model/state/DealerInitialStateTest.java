package blackjack.model.state;

import blackjack.model.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DealerInitialStateTest {

    @Test
    @DisplayName("딜러가 첫 분배에서 draw상태 도달 - 성공: 카드 점수가 16이하이면 딜러draw 상태로 변화")
    void change_to_draw_if_score_under_equal_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.TWO);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
        DealerInitialState dealerInitialState = new DealerInitialState();
        CardDeck cardDeck = new CardDeck(List.of(card1, card2));

        // then
        assertThat(dealerInitialState.draw(cardDeck, new HandCard())).isInstanceOf(DealerDrawState.class);
    }

    @Test
    @DisplayName("딜러가 첫 분배에서 stand 상태 도달 - 성공: 카드 점수가 16초과이면 stand 상태로 변화")
    void change_to_stand_if_score_over_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
        DealerInitialState dealerInitialState = new DealerInitialState();
        CardDeck cardDeck = new CardDeck(List.of(card1, card2));

        // then
        assertThat(dealerInitialState.draw(cardDeck, new HandCard())).isInstanceOf(StandState.class);
    }
}
