package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Dealer dealer = new Dealer();

        //when
        Hand actual = dealer.drawCardWhenStart(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(2);
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Dealer dealer = new Dealer();

        //when
        Hand actual = dealer.drawCard(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when & then
        assertThat(dealer.calculateTotalCardNumber()).isEqualTo(20);
    }

    @Test
    void 딜러가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(dealer.isOverBurstBound()).isFalse();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        //when & then
        assertThat(dealer.isOverDrawBound()).isTrue();
    }
}
