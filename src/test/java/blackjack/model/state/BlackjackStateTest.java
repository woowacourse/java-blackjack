package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.card.HandCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BlackjackStateTest {

    @Test
    @DisplayName("블랙잭 상태 생성 테스트 - 실패 : 카드 점수가 21이 아니면 예외발생.")
    void generate_blackjack_state_fail_if_score_not_21() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);

        //then
        assertThatThrownBy(() -> {
            new BlackjackState(new HandCard(List.of(card1, card2)));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수가 21이 아닌 카드들입니다. 블랙잭이 될 수 없습니다.");
    }

    @Test
    @DisplayName("블랙잭 상태 생성 테스트 - 성공 : 카드 점수가 21이면 성공")
    void generate_blackjack_state_success() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2);

        //when
        BlackjackState blackjackState = new BlackjackState(new HandCard(cards));

        //then
        assertThat(blackjackState.getHand()).containsExactly(card1, card2);
    }
}
