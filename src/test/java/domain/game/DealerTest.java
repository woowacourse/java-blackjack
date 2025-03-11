package domain.game;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        //when
        dealer.drawCard(DRAW_COUNT_WHEN_START);

        //then
        assertThat(dealer.getHand().getCards()).hasSize(2);
    }

    @Test
    void 딜러는_게임_시작_시_한_장의_카드를_공개한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));

        //when
        Card singleCard = dealer.getSingleCard();

        //then
        assertThat(singleCard.equals(new Card(Pattern.SPADE, CardNumber.TEN))).isTrue();
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);

        //when
        dealer.drawCard(DRAW_COUNT_WHEN_HIT);

        //then
        assertThat(dealer.getHand().getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
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
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(dealer.isOverBustBound()).isFalse();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Dealer dealer = new Dealer(cardDeck);
        Hand hand = dealer.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        //when & then
        assertThat(dealer.isOverDrawBound()).isTrue();
    }
    
}
