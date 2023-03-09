package blackjack.model.participant;

import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.DealerDrawState;
import blackjack.model.state.DealerInitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("딜러의 처음 분배 카드를 조회 - 실패 : 받은 카드가 없을 경우 예외 발생")
    void dealer_firstDistributed_card_fail() {
        //given
        Dealer dealer = new Dealer(new DealerInitialState());

        //then
        assertThatThrownBy(dealer::firstDistributedCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 분배 받지 않은 상태입니다.");
    }

    @Test
    @DisplayName("딜러의 처음 분배된 카드를 조회 - 성공 : 처음의 1장만을 노출한다.")
    void dealer_firstDistributed_card_success() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        List<Card> cards = List.of(card1, card2, card3, card4);
        CardDeck cardDeck = new CardDeck(cards);
        Dealer dealer = new Dealer(new DealerInitialState());

        // when
        dealer.draw(cardDeck);
        Map<String, List<Card>> firstDistributedCard = dealer.firstDistributedCard();
        //then
        assertThat(firstDistributedCard.get("딜러")).containsExactly(card4);
    }

    @Test
    @DisplayName("딜러는 점수가 16 초과이면 카드를 더 뽑지 못한다.")
    void dealer_can_not_draw_over_16() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);

        List<Card> cards = List.of(card4, card3);
        Dealer dealer = new Dealer(new DealerDrawState(), new HandCard(new ArrayList<>(List.of(card1, card2))));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.draw(cardDeck);

        //then
        assertThatThrownBy(() -> dealer.draw(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("스탠드 상태에서는 카드를 더 뽑을 수 없습니다.");
    }
}
