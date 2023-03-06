package blackjack.model.state;

import blackjack.model.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DealerInitialStateTest {

    @Test
    @DisplayName("딜러 처음 상태 생성 - 실패 : 카드가 비어있지 않으면 예외발생.")
    void generate_initial_state_fail_if_card_not_empty() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);

        //then
        assertThatThrownBy(() -> {
                    new DealerInitialState((new HandCard(List.of(card1))));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 상태의 카드는 비어있어야 합니다.");
    }

    @Test
    @DisplayName("딜러가 첫 분배에서 draw상태 도달 - 성공: 카드 점수가 16이하이면 딜러draw 상태로 변화")
    void change_to_draw_if_score_under_equal_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.TWO);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
        DealerInitialState dealerInitialState = new DealerInitialState(new HandCard());
        CardDeck cardDeck = new CardDeck(List.of(card1, card2));

        // then
        assertThat(dealerInitialState.draw(cardDeck)).isInstanceOf(DealerDrawState.class);
    }

    @Test
    @DisplayName("딜러가 첫 분배에서 stand 상태 도달 - 성공: 카드 점수가 16초과이면 stand 상태로 변화")
    void change_to_stand_if_score_over_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
        DealerInitialState dealerInitialState = new DealerInitialState(new HandCard());
        CardDeck cardDeck = new CardDeck(List.of(card1, card2));

        // then
        assertThat(dealerInitialState.draw(cardDeck)).isInstanceOf(StandState.class);
    }
}
