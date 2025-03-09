package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    void 카드_덱에서_카드_두_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Hand hand = new Hand();

        //when
        hand.drawCardWhenStart(cardDeck);

        //then
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    void 카드_덱에서_카드_한_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        Hand hand = new Hand();

        //when
        hand.drawCard(cardDeck);

        //then
        assertThat(hand.getCards()).hasSize(1);
    }

    @Test
    void 보유한_카드의_합계를_계산한다() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when
        int totalNumber = hand.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(22);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_11로_판단() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        int totalNumber = hand.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(21);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_1로_판단() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TWO));

        //when
        int totalNumber = hand.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(13);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_21초과해도_ace를_1로_판단() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when
        int totalNumber = hand.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(23);
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21초과_케이스() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(hand.isOverBustBound()).isTrue();
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21이하_케이스() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.NINE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(hand.isOverBustBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_11로_처리() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when & then
        assertThat(hand.isOverBustBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리하면_통과() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(hand.isOverBustBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리해도_버스트() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when & then
        assertThat(hand.isOverBustBound()).isTrue();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace_4개_케이스() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when & then
        assertThat(hand.isOverBustBound()).isFalse();
    }
}
