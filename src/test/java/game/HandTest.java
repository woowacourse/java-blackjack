package game;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    void 카드_덱에서_카드_두_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Hand hand = new Hand();

        //when
        hand.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_START));

        //then
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    void 카드_덱에서_카드_한_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Hand hand = new Hand();

        //when
        hand.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));

        //then
        assertThat(hand.getCards()).hasSize(1);
    }

    @Test
    void 보유한_카드의_합계를_계산한다() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN)));

        //when
        int totalNumber = hand.calculate();

        //then
        assertThat(totalNumber).isEqualTo(20);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_11로_판단() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.ACE)));

        //when
        int totalNumber = hand.calculate();

        //then
        assertThat(totalNumber).isEqualTo(21);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_1로_판단() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN),
                new Card(Pattern.SPADE, CardNumber.ACE)));

        //when
        int totalNumber = hand.calculate();

        //then
        assertThat(totalNumber).isEqualTo(21);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_21초과해도_ace를_1로_판단() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN),
                new Card(Pattern.SPADE, CardNumber.TWO),
                new Card(Pattern.SPADE, CardNumber.ACE)));

        //when
        int totalNumber = hand.calculate();

        //then
        assertThat(totalNumber).isEqualTo(23);
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21초과_케이스() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN),
                new Card(Pattern.SPADE, CardNumber.TWO)));

        //when & then
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21이하_케이스() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN)));

        //when & then
        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_11로_처리() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.ACE)));

        //when & then
        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리하면_통과() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN),
                new Card(Pattern.SPADE, CardNumber.ACE)));

        //when & then
        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리해도_버스트() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.NINE),
                new Card(Pattern.SPADE, CardNumber.TWO),
                new Card(Pattern.SPADE, CardNumber.ACE)
        ));

        //when & then
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace_4개_케이스() {
        //given
        Hand hand = new Hand();
        hand.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.ACE),
                new Card(Pattern.DIAMOND, CardNumber.ACE),
                new Card(Pattern.HEART, CardNumber.ACE),
                new Card(Pattern.CLOVER, CardNumber.ACE)
        ));

        //when & then
        assertThat(hand.isBust()).isFalse();
    }
}
