package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.card.HandCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustStateTest {

    @Test
    @DisplayName("버스트 상태 생성 테스트 - 실패 : 카드 점수가 21 이하이면 예외발생.")
    void generate_bust_state_fail_if_score_not_over_21() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.KING);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);

        //then
        assertThatThrownBy(() -> {
            new BustState(new HandCard(List.of(card1, card2)));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이하입니다. 버스트 상태가 될 수 없습니다.");
    }

    @Test
    @DisplayName("버스트 상태 생성 테스트 - 성공 : 카드 점수가 21초과이면 성공")
    void generate_bust_state_success() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.KING);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.JACK);
        Card card3 = Card.of(CardSuit.DIAMOND, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2, card3);

        //when
        BustState bustState = new BustState(new HandCard(cards));

        //then
        assertThat(bustState.getHand()).containsExactly(card1, card2, card3);
    }
}
