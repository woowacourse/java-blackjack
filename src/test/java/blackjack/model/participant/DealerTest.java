package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.DealerDrawState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DealerTest {

    @Test
    @DisplayName("딜러는 점수가 16 초과이면 카드를 더 뽑지 못한다.")
    void dealer_can_not_draw_over_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);

        List<Card> cards = List.of(card4, card3);
        Dealer dealer = new Dealer(new DealerDrawState(new Hand(new ArrayList<>(List.of(card1, card2)))));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);

        //then
        Assertions.assertThatThrownBy(() -> dealer.play(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("스탠드 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

}
