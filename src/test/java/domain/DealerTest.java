package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 16 이하라면 카드를 뽑는다")
    @Test
    void canHit() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.ACE);
        Card card2 = new Card(Symbol.HEART, CardRank.TWO);
        Card card3 = new Card(Symbol.SPADE, CardRank.KING);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(cards);

        int initialPoint = dealer.getCards().calculateTotalPoint();

        //when
        dealer.hit(cards);
        int actual = dealer.getCards().calculateTotalPoint();

        //then
        assertThat(actual).isNotEqualTo(initialPoint);
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 17 이상이라면 카드를 뽑지 않는다")
    @Test
    void cannotHit() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, CardRank.JACK);
        Card card2 = new Card(Symbol.HEART, CardRank.NINE);
        Card card3 = new Card(Symbol.SPADE, CardRank.KING);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(cards);

        int initialPoint = dealer.getCards().calculateTotalPoint();

        //when //then
        assertThatCode(() -> dealer.hit(cards))
                .isInstanceOf(IllegalStateException.class);

    }

}
