package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FinishedStateTest {

    @Test
    @DisplayName("총 합이 21 이고 카드가 2장이면 blackjack 이다")
    void finish_blackjack() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.QUEEN);
        List<Card> cards = List.of(card1, card2);
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
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.KING);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2, card3);
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
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.FOUR);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.TEN);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.SEVEN);
        List<Card> cards = List.of(card1, card2, card3);
        Hand hand = new Hand(cards);

        //when
        FinishedState state = FinishedState.checkFinishedState(hand);

        //then
        assertThat(state).isEqualTo(FinishedState.STAND);
    }

}
