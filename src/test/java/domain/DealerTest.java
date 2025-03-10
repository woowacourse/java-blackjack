package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 16 이하라면 카드를 뽑는다")
    @Test
    void canHit() {
        //given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Symbol.COLVER, Rank.ACE));
        cards.add(new Card(Symbol.HEART, Rank.TWO));
        cards.add(new Card(Symbol.SPADE, Rank.KING));

        Deck deck = Deck.from(cards);

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        int initialPoint = dealer.getHand().calculateTotalScore();

        //when
        dealer.hit(deck);
        int actual = dealer.getHand().calculateTotalScore();

        //then
        assertThat(actual).isNotEqualTo(initialPoint);
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 17 이상이라면 카드를 뽑지 않는다")
    @Test
    void cannotHit() {
        //given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Symbol.COLVER, Rank.JACK));
        cards.add(new Card(Symbol.HEART, Rank.NINE));
        cards.add(new Card(Symbol.SPADE, Rank.KING));

        Deck deck = Deck.from(cards);

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        int initialPoint = dealer.getHand().calculateTotalScore();

        //when
        dealer.hit(deck);
        int actual = dealer.getHand().calculateTotalScore();

        //then
        assertThat(actual).isEqualTo(initialPoint);
    }

}
