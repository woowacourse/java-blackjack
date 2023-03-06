package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.card.HandCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InitialStateTest {

    @Test
    @DisplayName("처음 상태 생성 테스트 - 실패 : 카드가 비어있지 않으면 예외발생.")
    void generate_initial_state_fail_if_card_not_empty() {
        //when
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);

        //then
        assertThatThrownBy(() -> {
            new InitialState(new HandCard(List.of(card1)));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 상태의 카드는 비어있어야 합니다.");
    }

    @Test
    @DisplayName("처음 상태 생성 테스트 - 성공 : 카드가 비어있으면 성공")
    void generate_initial_state_success() {
        //when
        InitialState initialState = new InitialState(new HandCard());

        //then
        assertThat(initialState.getHand()).isNullOrEmpty();
    }
}
