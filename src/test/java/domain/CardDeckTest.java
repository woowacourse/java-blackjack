package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    void 모든_카드를_생성한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when & then
        assertThat(cardDeck.getDeck()).hasSize(52);
    }

    @Test
    void 카드를_1장_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when
        Card actual = cardDeck.drawCard();

        //then
        Card expected = new Card(Pattern.SPADE, CardNumber.KING);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 카드를_1장_드로우하면_덱의_카드_수가_1개_줄어든다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when
        cardDeck.drawCard();

        //then
        assertThat(cardDeck.getDeck()).hasSize(51);
    }

    @Test
    void 게임_시작을_위해_카드를_2장_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when
        List<Card> actual = cardDeck.drawCardWhenStart();

        //then
        List<Card> expected = List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.QUEEN));
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void 카드들을_섞는다() {
        //given
        CardShuffler cardShuffler = new CardShuffler();
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());

        //when
        List<Card> actual = cardDeck.shuffle(cardShuffler);

        //then
        Assertions.assertThat(actual).isNotEqualTo(cardDeck.getDeck());
    }
}
