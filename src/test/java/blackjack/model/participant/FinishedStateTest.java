package blackjack.model.participant;

import blackjack.model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.model.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FinishedStateTest {

    @Test
    @DisplayName("총 합이 21 이고 카드가 2장이면 blackjack 이다")
    void finish_blackjack() {
        //given
        List<Card> cards = List.of(HEART_ACE, HEART_TEN);
        Hand hand = new Hand(cards);

        //when
        FinishedState state = FinishedState.checkFinishedState(hand);

        //then
        assertThat(state).isEqualTo(FinishedState.BLACKJACK);
    }

    @Test
    @DisplayName("총 합이 21 이상이면 Bust 이다")
    void finish_bust() {
        //given
        List<Card> cards = List.of(CLUB_JACK, HEART_TEN, HEART_JACK);
        Hand hand = new Hand(cards);

        //when
        FinishedState state = FinishedState.checkFinishedState(hand);

        //then
        assertThat(state).isEqualTo(FinishedState.BUST);
    }

    @Test
    @DisplayName("총 합이 21 이하면 stand 이다")
    void finish_stand() {
        //given
        List<Card> cards = List.of(CLUB_FOUR, HEART_TEN, HEART_SEVEN);
        Hand hand = new Hand(cards);

        //when
        FinishedState state = FinishedState.checkFinishedState(hand);

        //then
        assertThat(state).isEqualTo(FinishedState.STAND);
    }

}
